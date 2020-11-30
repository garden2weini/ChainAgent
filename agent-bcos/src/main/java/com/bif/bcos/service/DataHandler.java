package com.bif.bcos.service;

import org.com.fisco.BcosSDKTest;
import com.bif.bcos.diy.BifBlockParameter;
import org.fisco.bcos.sdk.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;
import org.fisco.bcos.web3j.protocol.core.methods.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

/**
 * 注意：推荐使用OracleJDK！！！
 * https://fisco-bcos-documentation.readthedocs.io/zh_CN/latest/docs/api.html
 * https://webasedoc.readthedocs.io/zh_CN/latest/
 * http://39.107.139.164:5002/WeBASE-Front
 * Web3j不是通过jsonrpc（8545-8548）方式的调用封装
 */
@Component
public class DataHandler {
    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);

    @Autowired
    private Web3j web3j;

    //@Scheduled(fixedDelay = 6000_000, initialDelay = 1_000)
    private void testBcosSdk() {
        BcosSDKTest bcosSDKTest = new BcosSDKTest();
        try {
            bcosSDKTest.testClient();
        } catch (ConfigException e) {
            e.printStackTrace();
        } catch (ContractException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(fixedDelay = 6000_000, initialDelay = 1_000)
    private void tmpBaseLogical() {
        //通过Web3j对象调用API接口getBlockNumber
        BigInteger blockNumberID = null;
        try {
            // getPbftView 返回节点所在指定群组内的最新PBFT视图。
            // NOTE: 通过getPbftView接口的返回信息可以判断是PBFT共识/Raft共识

            // getBlockNumber 返回节点指定群组内的最新区块高度
            blockNumberID = this.getLastBlockNumber();
            // getBlockByNumber 返回根据区块高度查询的区块信息
            this.getBlockByNumber(blockNumberID.toString());

            // getPeers 返回已连接的p2p节点信息
            this.getPeers();

            // getGroupPeers 返回指定群组内的共识节点和观察节点的ID列表（但不做类型区分）
            this.getGroupPeers();
            // getObserverList 返回指定群组内的观察节点列表
            // getSealerList 返回指定群组内的共识节点列表

            // getGroupList 节点所属群组的群组ID列表
            List<String> grpList = web3j.getGroupList().send().getGroupList();
            for(String grp : grpList) {
                System.out.println("Group:" + grp);
            }


            // getNodeIDList 节点本身和已连接p2p节点的ID列表
            // getSealerList 返回指定群组内的共识节点列表
            // getObserverList 返回指定群组内的观察节点列表
            // getConsensusStatus 返回指定群组内的共识状态信息
            // getPendingTransactions 返回待打包的交易信息
            // getPendingTxSize 返回待打包的交易数量
            // getTotalTransactionCount 返回当前交易总数和区块高度
            // getSystemConfigByKey 返回根据key值查询的value值（支持tx_count_limit和tx_gas_limit）
            // call 执行一个可以立即获得结果的请求，无需区块链共识
            // *generateGroup 根据群组ID及创世块参数创建新的群组，本接口仅在兼容性版本为2.2.0及以后的版本有效
            // startGroup 根据群组ID启动相应的群组，本接口仅在兼容性版本为2.2.0及以后的版本有效
            // queryGroupStatus 根据群组ID查询相应群组的状态
            // getNodeInfo 获取请求节点的NodeID，Topic等信息。NodeInfo: 获取节点信息，包括节点所在的机构、节点名、节点NodeID以及节点订阅的Topic
            // getBatchReceiptsByBlockNumberAndRange 根据区块高度和交易范围，批量返回指定群组的交易回执信息。
            // getTotalTransactionCount 返回当前交易总数、失败的交易总数和区块高度
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getGrpStatus() {
        System.out.println("根据群组ID查询相应群组的状态......");
        //web3j.getConsensusStatus()
    }

    private void getGroupPeers() throws IOException {
        System.out.println("返回指定群组内的共识节点和观察节点的ID列表（但不做类型区分）......");
        GroupPeers groupPeers = web3j.getGroupPeers().send();
        for(String grpPeer : groupPeers.getGroupPeers()) {
            System.out.println("GroupPeer:" + grpPeer);
        }
        System.out.println("#####################################");
    }

    private void getPeers() throws IOException {
        System.out.println("返回已连接的p2p节点信息......");
        Peers peers = web3j.getPeers().send();
        for(Peers.Peer peer: peers.getPeers()) {
            System.out.println("IPAndPort:" + peer.getIPAndPort());
            System.out.println("NodeID:" + peer.getNodeID());
            System.out.println("Peers->Topic Info start.");
            for(String topic: peer.getTopic()) {
                System.out.println("Topic:" + topic);
            }
            System.out.println("Peers->Topic Info end.");
        }
        System.out.println("#####################################");
    }

    // 返回节点指定群组内的最新区块高度
    private BigInteger getLastBlockNumber() throws IOException {
        System.out.println("返回节点指定群组内的最新区块高度......");
        BlockNumber blockNumber = web3j.getBlockNumber().send();
        BigInteger blockNumberID = blockNumber.getBlockNumber();
        System.out.println("LastBlockNumber=" + blockNumberID);
        System.out.println("#####################################");
        return blockNumberID;
    }

    // 返回根据区块高度查询的区块信息
    private void getBlockByNumber(String blockNumber) throws IOException {
        System.out.println("返回根据区块高度查询的区块信息......");
        DefaultBlockParameter param = new BifBlockParameter(blockNumber);
        BcosBlock bcosBlock = web3j.getBlockByNumber(param, true).send();
        BcosBlock.Block block = bcosBlock.getBlock();
        System.out.println("BlockByNumber-BlockInfo\n:" + block.toString());
        System.out.println("区块高度：" + block.getNumber());
        System.out.println("区块状态：none？");
        System.out.println("区块Hash：" + block.getHash());
        List<BcosBlock.TransactionResult> txList = block.getTransactions();
        System.out.println("交易笔数：" + txList.size());
        for(BcosBlock.TransactionResult tx : txList) {
            BcosBlock.TransactionObject obj = (BcosBlock.TransactionObject)tx.get();
            System.out.println("--交易列表-hash：" + obj.getHash());
            System.out.println("--交易列表-from：" + obj.getFrom());
            // NOTE：接收者的地址，创建合约交易的该值为0x0000000000000000000000000000000000000000
            System.out.println("--交易列表-to：" + obj.getTo());
            System.out.println("--交易列表-index：" + obj.getTransactionIndex());
            System.out.println("--交易列表-creates：" + obj.getCreates());
            System.out.println("--交易列表-交易的输入：" + obj.getInput());
            System.out.println("--交易列表-签名：" + obj.getS());
            System.out.println("--交易列表-大小：none");
            System.out.println("--交易列表-调用链码：none");
        }
        System.out.println("播报方：none?");
        System.out.println("块大小：none?");
        System.out.println("上一区块Hash：" + block.getParentHash());
        System.out.println("下一区块Hash：none？");

        System.out.println("共识节点序号：" + block.getSealer());
        System.out.println("共识节点列表：" + block.getSealerList());
        System.out.println("播报方：none？");
        System.out.println("交易Root Hash：none？");
        System.out.println("结果Root Hash：none？");

        System.out.println("#####################################");

    }
}
