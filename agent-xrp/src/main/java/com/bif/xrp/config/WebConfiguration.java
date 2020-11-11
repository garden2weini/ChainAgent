package com.bif.xrp.config;

import com.bif.nettyclient.NettyClientConnector;
import com.bif.xrp.client.XrpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class WebConfiguration {

    @Bean
    public XrpClient xrpClient(ApplicationProperties properties) {
        XrpClient client = new XrpClient(properties.getChainEntry().getAddress());
        return client;
    }

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }


}
