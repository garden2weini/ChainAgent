package com.bif.bitcoin.service;

import com.bif.bitcoin.config.ApplicationProperties;
import com.bif.nettyclient.NettyClientConnector;
import com.bif.service.BaseDataHandler;
import com.bif.vm.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DataHandler extends BaseDataHandler {

    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);
    private final String chainType;
    private final String chainId;
    private final String nodeKey;

    // 最新高度
    public static AtomicInteger bestBlockNumber = new AtomicInteger(0);

    // 同步高度
    public static AtomicInteger syncBlockNumber = new AtomicInteger();

    @Autowired
    private NettyClientConnector nettyClientConnector;

    @Autowired
    private BitcoindRpcClient btcClient;

    @Autowired
    private ApplicationProperties properties;

    private final Lock lock = new ReentrantLock();

    public DataHandler(ApplicationProperties properties) {
        this.chainType = properties.getChain().getType();
        this.chainId = properties.getChain().getId();
        this.nodeKey = properties.getNode().getKey();
    }

    /**
     * 客户端版本
     */
    @Scheduled(fixedDelay = 5_000, initialDelay = 5_000)
    private void getBlockNumber() {
        try {
            // 区块链最新高度
            bestBlockNumber = new AtomicInteger(btcClient.getBlockCount());
            Request request = new Request("MESSAGE_ID_BLOCK_NUMBER", chainType, chainId, nodeKey, "blockNumber", "push", bestBlockNumber.toString());

            //Response response = sendRequest(request);
            System.out.println("................................");
            //System.out.println(response.toString());
            System.out.println(request);
            System.out.println("................................");
        } catch (Exception e) {
            logger.error("", e);
        }
    }
}
