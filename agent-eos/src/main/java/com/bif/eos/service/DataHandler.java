package com.bif.eos.service;

import com.bif.eos.config.ApplicationProperties;
import com.bif.nettyclient.NettyClientConnector;
import com.bif.service.BaseDataHandler;
import io.eblock.eos4j.Rpc;
import io.eblock.eos4j.api.vo.Block;
import io.eblock.eos4j.api.vo.ChainInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.SocketOption;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * NOTE: Use io.eblock.eos-eos4j for EOS client.
 */
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
    Rpc eosRpc;

    @Autowired
    private ApplicationProperties properties;

    private final Lock lock = new ReentrantLock();

    public DataHandler(ApplicationProperties properties) {
        this.chainType = properties.getChain().getType();
        this.chainId = properties.getChain().getId();
        this.nodeKey = properties.getNode().getKey();
    }

    /**
     * 区块链获取逻辑梳理
     */
    @Scheduled(fixedDelay = 6000_000, initialDelay = 1_000)
    private void tmpBaseLogical() {
        ChainInfo chainInfo = eosRpc.getChainInfo();
        String headBlockId = chainInfo.getHeadBlockId();
        String headBlockNum = chainInfo.getHeadBlockNum();
        String chainId = chainInfo.getChainId();
        System.out.println("ChainInfo.HeadBlockId:" + headBlockId);
        System.out.println("ChainInfo.HeadBlockNum:" + headBlockNum);
        System.out.println("ChainInfo.ChainId:" + chainId);
        Block block = eosRpc.getBlock(headBlockId);

        while(null != block) {
            String actionMroot = block.getActionMroot();
            String transaction = block.getTransactionMroot();
            String previous = block.getPrevious();
            System.out.println("Block.ActionMroot:" + actionMroot);
            System.out.println("Block.TransactionMroot:" + transaction);
            System.out.println("Block.Previous:" + previous);

            try {
                block = eosRpc.getBlock(previous);
            } catch (Exception e) {
                e.printStackTrace();
                block = null;
            }
        }
    }


}
