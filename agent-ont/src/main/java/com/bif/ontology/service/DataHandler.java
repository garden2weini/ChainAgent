package com.bif.ontology.service;

import com.bif.nettyclient.NettyClientConnector;
import com.bif.ontology.config.ApplicationProperties;
import com.bif.service.BaseDataHandler;
import com.github.ontio.OntSdk;
import com.github.ontio.core.block.Block;
import com.github.ontio.network.exception.ConnectorException;
import com.github.ontio.sdk.exception.SDKException;
import com.github.ontio.sdk.manager.ConnectMgr;
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
    private OntSdk ontClient;

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
            //ConnectMgr conn = ontClient.getRestful();
            //ConnectMgr conn = ontClient.getConnect();
            ConnectMgr conn = ontClient.getRpc();
            int blockHeight = conn.getBlockHeight();
            System.out.println("BlockHeight:" + blockHeight);
            Block block = conn.getBlock(1);
            System.out.println("block:" + block);
            int networkId = conn.getNetworkId();
            System.out.println("NetworkId:" + networkId);
        } catch (SDKException e) {
            e.printStackTrace();
        } catch (ConnectorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
