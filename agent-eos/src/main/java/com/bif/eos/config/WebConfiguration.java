package com.bif.eos.config;

import client.EosApiClientFactory;
import client.EosApiRestClient;
import com.bif.eos.client.EosClient;
import com.bif.nettyclient.NettyClientConnector;
import io.eblock.eos4j.Rpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@Configuration
public class WebConfiguration {
    @Bean
    @Deprecated
    public EosApiRestClient eosApiRestClient(ApplicationProperties properties) {
        EosApiRestClient eosApiRestClient = EosApiClientFactory.newInstance(properties.getChainEntry().getAddress()).newRestClient();
        return eosApiRestClient;
    }

    @Bean
    public Rpc eosRpc(ApplicationProperties properties) {
        Rpc eosRpc = new Rpc(properties.getChainEntry().getAddress());
        return eosRpc;
    }

    @Bean
    public EosClient eosClient(ApplicationProperties properties) {
        EosClient eosClient = new EosClient(properties.getChainEntry().getAddress());
        return eosClient;
    }

    @Bean(destroyMethod = "shutdown")
    public NettyClientConnector nettyClientConnector(ApplicationProperties properties) throws URISyntaxException {
        ApplicationProperties.Server server = properties.getServer();
        return new NettyClientConnector(server.getHost(),server.getPort(),server.getConnectTimeout());
    }
}
