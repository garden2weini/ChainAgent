package com.bif.ethereumagent.config;

import com.bif.ethereumagent.client.NettyClientConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class WebConfiguration {

    @Bean
    public Web3j web3j(ApplicationProperties properties) {
        Web3j web3j = Web3j.build(new HttpService(properties.getEthereum().getAddress()));
        return web3j;
    }

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }


}
