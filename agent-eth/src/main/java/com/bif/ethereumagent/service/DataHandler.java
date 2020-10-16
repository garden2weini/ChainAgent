package com.bif.ethereumagent.service;

import com.alibaba.fastjson.JSONObject;
import com.bif.nettyclient.NettyClientConnector;
import com.bif.ethereumagent.config.ApplicationProperties;
import com.bif.ethereumagent.config.EventConstants;
import com.bif.util.FastJsonUtil;
import com.bif.ethereumagent.vm.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.*;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class DataHandler {

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
    private Web3j web3j;

    @Autowired
    private ApplicationProperties properties;

    private final Lock lock = new ReentrantLock();

    public DataHandler(ApplicationProperties properties) {
        this.chainType = properties.getChain().getType();
        this.chainId = properties.getChain().getId();
        this.nodeKey = properties.getNode().getKey();
    }

    //@Scheduled(fixedDelay = 10_000, initialDelay = 30_000)
    public final void scheduled() {
        // start a single new thread:
        new Thread(() -> {
            try {
                if (lock.tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        logger.info("[{}] start schedule job.", "TEST");
                        web3ClientVersion();
                        blockNumber();
                        netPeerCount();
                        ethProtocolVersion();
                        ethGasPrice();
                        ethSyncing();
                        netListening();
                        logger.info("[{}] end schedule job.", "TEST");
                    } catch (Exception e) {
                        logger.error("Exception when run block chain: " + e.getMessage(), e);
                    } finally {
                        lock.unlock();
                    }
                } else {
                    logger.info("[{}] skip schedule job for schedule job is still running.");
                }
            } catch (InterruptedException e) {
                logger.warn("InterruptedException when run scheduled job.", e);
            }
        }).start();
    }

    @Scheduled(fixedDelay = 5_000, initialDelay = 5_000)
    public final void syncBlockNumber() throws IOException {
        // 目前同步高度
        CommonRequest commonRequest = new CommonRequest();
        commonRequest.setNodeId(properties.getNode().getId());
        commonRequest.setAccessToken(properties.getNode().getAccessToken());
        commonRequest.setChainName(properties.getChain().getId());
        commonRequest.setEvent(EventConstants.GET_BLOCK);
        EthBlock block =
                web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(2000000)), false).send();
        BlockInfoVM blockInfoVM = new BlockInfoVM();
        blockInfoVM.setBlock(block.getBlock().getHash());
        blockInfoVM.setHeight(block.getBlock().getNumber().longValue());
        commonRequest.setParams(blockInfoVM);
        //Request request = new Request("MESSAGE_ID_BLOCK_COUNT", chainType, chainId, nodeKey, "block", "pull", "");
        CommonResponse response = sendRequest(commonRequest);
        logger.info("返回数据{}",response);

    }

    /**
     * 客户端版本
     */
    private void web3ClientVersion() {
        try {
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            Request request = new Request("MESSAGE_ID_CLIENT_VERSION", chainType, chainId, nodeKey, "clientVersion", "push", clientVersion);
            Response response = sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 区块数量
     */
    private void blockNumber() {
        try {
            EthBlockNumber ethBlockNumber = web3j.ethBlockNumber().send();
            BigInteger blockNumber = ethBlockNumber.getBlockNumber();
            bestBlockNumber.set(blockNumber.intValue());
            Request request = new Request("MESSAGE_ID_BLOCK_NUMBER", chainType, chainId, nodeKey, "blockNumber", "push", blockNumber.toString());
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 连接的节点数
     */
    private void netPeerCount() {
        try {
            NetPeerCount netPeerCount = web3j.netPeerCount().send();
            BigInteger peerCount = netPeerCount.getQuantity();
            Request request = new Request("MESSAGE_ID_PEER_COUNT", chainType, chainId, nodeKey, "connections", "push", peerCount.toString());
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 协议版本
     */
    private void ethProtocolVersion() {
        try {
            EthProtocolVersion ethProtocolVersion = web3j.ethProtocolVersion().send();
            String protocolVersion = ethProtocolVersion.getProtocolVersion();
            Request request = new Request("MESSAGE_ID_PROTOCOL_VERSION", chainType, chainId, nodeKey, "protocolVersion", "push", protocolVersion);
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 当前gas price
     */
    private void ethGasPrice() {
        try {
            EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
            BigInteger gasPrice = ethGasPrice.getGasPrice();
            Request request = new Request("MESSAGE_ID_GAS_PRICE", chainType, chainId, nodeKey, "gasPrice", "push", gasPrice.toString());
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * 是否在同步区块
     */
    private void ethSyncing() {
        try {
            EthSyncing ethSyncing = web3j.ethSyncing().send();
            Boolean isSyncing = ethSyncing.isSyncing();
            Request request = new Request("MESSAGE_ID_SYNCING", chainType, chainId, nodeKey, "syncing", "push", isSyncing.toString());
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    /**
     * net_listening
     */
    private void netListening() {
        try {
            NetListening netListening = web3j.netListening().send();
            Boolean result = netListening.isListening();
            Request request = new Request("MESSAGE_ID_LISTENING", chainType, chainId, nodeKey, "listening", "push", result.toString());
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public void syncBlock() {
//        if (syncBlockNumber == null) {
//            // 目前同步高度
//            Request request = new Request("MESSAGE_ID_BLOCK_COUNT", chainType, chainId, nodeKey, "block", "pull", "");
//            sendRequest(request);
//            return;
//        }
        int blockNumber = syncBlockNumber.get() + 1;
        if (bestBlockNumber.get() - blockNumber < 3) {
            return;
        }

        try {
            //todo blockNumber要到很大才会有数据
            EthBlock block = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(BigInteger.valueOf(blockNumber)), false).send();
            logger.info(JSONObject.toJSONString(block));
            List<EthBlock.TransactionResult> list = block.getBlock().getTransactions();
            for (EthBlock.TransactionResult transaction : list) {
                logger.info(JSONObject.toJSONString(transaction));
                EthTransaction ethTransaction = web3j.ethGetTransactionByHash(transaction.toString()).send();
                Request request = new Request("MESSAGE_ID_TRANSACTION", chainType, chainId, nodeKey, "transaction", "push", JSONObject.toJSONString(ethTransaction));
                Response txRes = sendRequest(request);
                EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(transaction.toString()).send();
                request = new Request("MESSAGE_ID_TRANSACTION", chainType, chainId, nodeKey, "transactionReceipt", "push", JSONObject.toJSONString(ethGetTransactionReceipt));
                Response txReceiptRes = sendRequest(request);
                if (!"success".equals(txReceiptRes.status)) {
                    throw new RuntimeException("data is not been received");
                }
            }
            Request request = new Request("MESSAGE_ID_BLOCK", chainType, chainId, nodeKey, "block", "push", JSONObject.toJSONString(block));
            sendRequest(request);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    private Response sendRequest(Request request) {
        Response response = nettyClientConnector.requestSync(request,
                FastJsonUtil::toString,
                (json) -> FastJsonUtil.toObject((String) json, Response.class));
        logger.info("request:{},response{}", request, response);
        return response;
    }

    private CommonResponse sendRequest(CommonRequest request) {
        CommonResponse response = nettyClientConnector.requestSync(request,
                FastJsonUtil::toString,
                (json) -> FastJsonUtil.toObject((String) json, CommonResponse.class));
        logger.info("request:{},response{}", request, response);
        return response;
    }

}
