package com.bif.nettyclient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * TODO: uncomment //this.promise.*
 */
public class SocketHandler extends SimpleChannelInboundHandler<String> implements ResultHandler {

    private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    private volatile ChannelPromise promise;

    private volatile Object result;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        this.result = msg;
        if (null != this.promise) {
            this.promise.setSuccess();
            this.promise = null;
        }
        logger.info("Client receiver msg : {}", result);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        //当链接断开都会触发inactive方法
        if (null != this.promise) {
            this.promise.setFailure(new IllegalStateException("connection has been closed!"));
            this.promise = null;
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("handler is throw exception", cause);
    }

    @Override
    public void setPromise(ChannelPromise promise) {
        this.promise = promise;
    }

    @Override
    public Object getResult() {
        Object receive = result;
        result = null;
        return receive;
    }
}
