package com.bif.ontology.config;

import com.bif.nettyclient.NettyClientConnector;
import com.github.ontio.OntSdk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;

@Configuration
public class WebConfiguration {

    @Bean
    public OntSdk ontClient(ApplicationProperties properties) {
        OntSdk ontSdk = com.github.ontio.OntSdk.getInstance();
        ontSdk.setRpc(properties.getChainEntry().getAddress());
        return ontSdk;
    }

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }


}
