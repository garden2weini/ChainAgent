package com.bif.bitcoin.service;

import com.bif.bitcoin.client.BitcoinClientX;
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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
     * 区块链获取逻辑梳理
     */
    @Scheduled(fixedDelay = 600_000, initialDelay = 1_000)
    private void tmpBaseLogical() {
        // 获取当前区块链链接数
        long connCount = btcClient.getConnectionCount();
        System.out.println("ConnectionCount:" + connCount);
        // 获取当前算力难度
        BigDecimal difficulty = btcClient.getDifficulty();
        System.out.println("Difficulty:" + difficulty);

        // 获取最新区块hash
        String lastBlockHash = btcClient.getBestBlockHash();
        System.out.println("BestBlockHash:" + lastBlockHash);
        // 获取最新区块数
        int blockCount = btcClient.getBlockCount();
        System.out.println("BlockCount:" + blockCount);
        // 获取区块信息(高度 or Hash)
        BitcoindRpcClient.Block block = btcClient.getBlock(blockCount);
        // NOTE: 从最新区块开始向前获取所有区块。然后循环获取前一个区块，直到获取到创世区块
        while(null != block) {
            //System.out.println("Block:" + block);
            // 获取当前区块Hash
            String blockHash = block.hash();
            //System.out.println("blockHash:" + lastBlockHash);
            // 轮询获取区块中的所有交易信息
            List<String> txs = block.tx();
            for(int i = 0; i< txs.size(); i++) {
                String txId = txs.get(i);
                //System.out.println(block.height() + ":" + txId);
                // 获取特定blockHash->txId的的交易信息
                BitcoindRpcClient.RawTransaction rawTransaction = ((BitcoinClientX)btcClient).getRawTransactionX(txId, blockHash);
                System.out.println(block.height() + ":" + "RawTransaction:" + rawTransaction);

            }

            // 获取前一个区块，并进入下一个循环
            block = block.previous();
        }

    }

    /**
     * sandbox
     */
    //@Scheduled(fixedDelay = 5_000, initialDelay = 5_000)
    private void sandbox() {
        try {
            // 区块链最新高度
            bestBlockNumber = new AtomicInteger(btcClient.getBlockCount());
            Request request = new Request("MESSAGE_ID_BLOCK_NUMBER", chainType, chainId, nodeKey, "blockNumber", "push", bestBlockNumber.toString());

            ArrayList address = ((BitcoinClientX)btcClient).getNodeAddresses();
            LinkedHashMap memInfo = ((BitcoinClientX)btcClient).getMemoryInfo();
            LinkedHashMap chainTxStats = ((BitcoinClientX)btcClient).getChainTxStats();
            //Response response = sendRequest(request);
            System.out.println("................................");
            //System.out.println(response.toString());
            System.out.println(address);
            System.out.println(memInfo);
            System.out.println(chainTxStats);
            System.out.println(request);
            System.out.println("................................");

        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * NOTE: 对照需求列出所需要的链接口。just调研接口 不能执行
     */
    private void tmp() {
        // 1 获取最新区块hash
        String lastBlockHash = btcClient.getBestBlockHash();
        System.out.println("BestBlockHash:" + lastBlockHash);
        // 2 获取最新区块数
        int blockCount = btcClient.getBlockCount();
        System.out.println("BlockCount:" + blockCount);
        // 3 获取某区块高度的区块Hash
        String blockHash = btcClient.getBlockHash(100);
        System.out.println("BlockHash:" + blockHash);
        // 4 获取区块信息(高度 or Hash)
        BitcoindRpcClient.Block block = btcClient.getBlock(100);
        System.out.println("Block:" + block);
        // 5 获取交易信息...
        // txId: The TXID of the transaction containing the output to get, encoded as hex in RPC byte order
        String txId = "";
        BitcoindRpcClient.RawTransaction rawTransaction = btcClient.getRawTransaction(txId);
        System.out.println("RawTransaction:" + rawTransaction);
        // 8. 获取链交易状态数据(getchaintxstats)
        LinkedHashMap chainTxStats = ((BitcoinClientX)btcClient).getChainTxStats();
        System.out.println("ChainTxStats:" + chainTxStats);
        // 9. 获取区块链概览信息
        BitcoindRpcClient.BlockChainInfo blockChainInfo = btcClient.getBlockChainInfo();
        System.out.println("BlockChainInfo:" + blockChainInfo);
        // 10. 获取内存使用(getmempoolinfo)
        LinkedHashMap memInfo = ((BitcoinClientX)btcClient).getMemoryInfo();
        System.out.println("MemoryInfo:" + memInfo);
        // 11. 获取当前节点信息(getnodeaddresses)
        ArrayList addresses = ((BitcoinClientX)btcClient).getNodeAddresses();
        System.out.println("NodeAddresses:" + addresses);
        // 12. 获取utxo信息
        txId = "";
        // vout : The output index number (vout) of the output within the transaction
        long vout = 1;
        BitcoindRpcClient.TxOut txOut = btcClient.getTxOut(txId, vout);
        System.out.println("TxOut:" + txOut);
    }
}
