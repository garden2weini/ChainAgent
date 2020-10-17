package com.bif.bitcoin.config;

import com.bif.bitcoin.client.BitcoinClientX;
import com.bif.nettyclient.NettyClientConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

@Configuration
public class WebConfiguration {
    @Bean
    public BitcoindRpcClient bitcoindRpcClient(ApplicationProperties properties) {
        BitcoindRpcClient client = null;
        try {
            //client = new BitcoinJSONRPCClient(properties.getBitcoin().getAddress());
            client = new BitcoinClientX(properties.getBitcoin().getAddress());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }


}
