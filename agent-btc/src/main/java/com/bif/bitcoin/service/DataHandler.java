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

import java.math.BigDecimal;
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
            this.tmp();
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * NOTE: 对照需求列出所需要的链接口
     */
    private void tmp() {
        // 1 获取最新区块hash
        String lastBlockHash = btcClient.getBestBlockHash();
        // 2 获取最新区块数
        int blockCount = btcClient.getBlockCount();
        // 3 获取某区块高度的区块Hash
        String blockHash = btcClient.getBlockHash(100);
        // 4 获取区块信息(高度 or Hash)
        BitcoindRpcClient.Block block = btcClient.getBlock(100);
        // 5 获取交易信息...
        // txId: The TXID of the transaction containing the output to get, encoded as hex in RPC byte order
        String txId = "";
        BitcoindRpcClient.RawTransaction rawTransaction = btcClient.getRawTransaction(txId);
        // 6. 获取区块链链接数
        long connCount = btcClient.getConnectionCount();
        // 7. 获取算力难度
        BigDecimal difficulty = btcClient.getDifficulty();
        // 8. Missing获取链交易状态数据(getchaintxstats)

        // 9. 获取区块链概览信息
        BitcoindRpcClient.BlockChainInfo blockChainInfo = btcClient.getBlockChainInfo();
        // 10. Missing获取内存使用(getmempoolinfo)

        // 11. none获取当前节点信息(getnodeaddresses)

        // 12. 获取utxo信息
        txId = "";
        // vout : The output index number (vout) of the output within the transaction
        long vout = 1;
        BitcoindRpcClient.TxOut txOut = btcClient.getTxOut(txId, vout);
    }
}
