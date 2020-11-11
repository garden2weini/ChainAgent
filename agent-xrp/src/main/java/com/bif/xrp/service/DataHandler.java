package com.bif.xrp.service;

import com.bif.nettyclient.NettyClientConnector;
import com.bif.xrp.client.XrpClient;
import com.bif.xrp.config.ApplicationProperties;
import com.bif.service.BaseDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class DataHandler extends BaseDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);
    private final String chainType;
    private final String chainId;
    private final String nodeKey;

    @Autowired
    private XrpClient xrpClient;

    @Autowired
    private NettyClientConnector nettyClientConnector;

    @Autowired
    private ApplicationProperties properties;

    public DataHandler(ApplicationProperties properties) {
        this.chainType = properties.getChain().getType();
        this.chainId = properties.getChain().getId();
        this.nodeKey = properties.getNode().getKey();
    }

    @Scheduled(fixedDelay = 1000_000, initialDelay = 1_000)
    public final void scheduled() {
        try {

            //System.out.println("NetworkId:" + networkId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
