package com.bif.bitcoin.config;

import com.bif.nettyclient.NettyClientConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class WebConfiguration {
    /*
    @Bean
    public Web3j web3j(ApplicationProperties properties) {
        Web3j web3j = Web3j.build(new HttpService(properties.getEthereum().getAddress()));
        return web3j;
    }*/

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }


}
