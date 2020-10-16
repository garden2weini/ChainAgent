package com.bif.nettyclient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClientConnector {

    private final Logger logger = LoggerFactory.getLogger(NettyClientConnector.class);

    private final String host;

    private final int port;

    private final int connectTimeout;

    private EventLoopGroup workerGroup;

    private Bootstrap bootstrap;

    private ThreadLocal<Channel> threadChannel;

    public NettyClientConnector(String host, int port, int connectTimeout) {
        this.host = host;
        this.port = port;
        this.connectTimeout = connectTimeout;
        this.threadChannel = new ThreadLocal<>();
        this.init();
    }

    public void init() {
        open();
    }

    /**
     * 如果在非正常状态下断开连接，将会尝试重新与服务器建立连接
     */
    private void open() {
        if (workerGroup == null) {
            workerGroup = new NioEventLoopGroup();
        }
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(workerGroup);
        this.bootstrap.channel(NioSocketChannel.class);
        this.bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        this.bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout);

        this.bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {
            @Override
            protected void initChannel(NioSocketChannel ch) {
                ch.pipeline().addLast(new StringDecoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast(new StringEncoder(CharsetUtil.UTF_8));
                ch.pipeline().addLast(new SocketHandler());
            }
        });
    }

    private Channel connect() {
        Channel channel = this.threadChannel.get();
        if (channel != null && channel.isWritable()) {
            System.out.println(channel);
            return channel;
        }
        this.threadChannel.remove();
        try {
            ChannelFuture connect = this.bootstrap.connect(this.host, this.port).sync();
            connect.addListener(future -> {
                if (future.isSuccess()) {
                    logger.info("Client is connection .");
                } else {
                    connect.channel().close();
                    this.bootstrap.connect(this.host, this.port);
                }
                connect.channel().closeFuture().addListener(cfl -> {
                    logger.info("Client [" + connect.channel().localAddress().toString() + "] connect failed ?!");
                });
            });
            ChannelFuture f = connect.sync();
            channel = f.channel();
            f.sync();
            this.threadChannel.set(channel);
            return channel;
        } catch (InterruptedException e) {
            logger.error("create channel connect throw exception", e);
            throw new RuntimeException();
        }
    }

    public void shutdown() {
        if (null != threadChannel.get()) {
            threadChannel.get().close();
        }
        this.workerGroup.shutdownGracefully();
    }

    public <T> T requestSync(Object msg, MessageEncoder encoder, MessageDecoder<T> decoder) {
        Channel channel = connect();
        if (!channel.isWritable()) {
            throw new RuntimeException();
        }
        SocketHandler last = (SocketHandler) channel.pipeline().last();
        boolean success = false;
        try {
            ChannelPromise promise = channel.newPromise();
            channel.writeAndFlush(encoder.encode(msg));
            last.setPromise(promise);
            promise.sync();
            success = promise.isSuccess();
        } catch (InterruptedException e) {
            logger.error("InterruptedException", e);
        }
        if (success) {
            Object result = last.getResult();
            String json = (String) result;
            return decoder.decode(json);
        } else {
            return decoder.decode("{}");
        }
    }
}
