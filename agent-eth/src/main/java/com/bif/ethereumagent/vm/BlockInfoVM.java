package com.bif.ethereumagent.vm;

import java.io.Serializable;

/**
 * chainId: 链ID
 * hash: 区块Hash
 * preHash: 上一区块Hash
 * height: 区块高度
 * timestamp: 出块时间
 * broadcaster: 播报方
 * txCount: 交易数量
 * pendingTxCount: 未确认交易数
 * contractNum: 合约总量
 * dappNum: Dapp总量
 * miner: 出块人
 * blockSize: 区块大小
 * reward: 区块奖励
 * amount: 区块交易额
 * fee: 区块交易费
 * coinAddress: 持币地址数
 * difficulty: 挖矿难度
 * computing: 算力
 * tps: tps数
 * interval: 出块间隔
 * consensus: 共识机制
 * occupation: 空间占用
 * txHashRoot: 交易root哈希
 * statusHashRoot: 状态root哈希
 * resultHashRoot: 结果root哈希
 */
public class BlockInfoVM implements Serializable {

    private transient static final long serialVersionUID = -1866325576771685216L;

    /**
     * blockNumber|blockHash|latest
     */
    private String block;

    private String chainId;

    private String hash;

    private String preHash;

    private Long height;

    private Long timestamp;

    private String broadcaster;

    private Long txCount;

    private Long pendingTxCount;

    private Long contractNum;

    private Long dappNum;

    private String miner;

    private Long blockSize;

    private Long reward;

    private Long amount;

    private Long fee;

    private Long coinAddress;

    private Long difficulty;

    private String computing;

    private String tps;

    private Long interval;

    private String consensus;

    private String occupation;

    private String txHashRoot;

    private String statusHashRoot;

    private String resultHashRoot;

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreHash() {
        return preHash;
    }

    public void setPreHash(String preHash) {
        this.preHash = preHash;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getBroadcaster() {
        return broadcaster;
    }

    public void setBroadcaster(String broadcaster) {
        this.broadcaster = broadcaster;
    }

    public Long getTxCount() {
        return txCount;
    }

    public void setTxCount(Long txCount) {
        this.txCount = txCount;
    }

    public Long getPendingTxCount() {
        return pendingTxCount;
    }

    public void setPendingTxCount(Long pendingTxCount) {
        this.pendingTxCount = pendingTxCount;
    }

    public Long getContractNum() {
        return contractNum;
    }

    public void setContractNum(Long contractNum) {
        this.contractNum = contractNum;
    }

    public Long getDappNum() {
        return dappNum;
    }

    public void setDappNum(Long dappNum) {
        this.dappNum = dappNum;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public Long getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(Long blockSize) {
        this.blockSize = blockSize;
    }

    public Long getReward() {
        return reward;
    }

    public void setReward(Long reward) {
        this.reward = reward;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getCoinAddress() {
        return coinAddress;
    }

    public void setCoinAddress(Long coinAddress) {
        this.coinAddress = coinAddress;
    }

    public Long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    public String getComputing() {
        return computing;
    }

    public void setComputing(String computing) {
        this.computing = computing;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

    public Long getInterval() {
        return interval;
    }

    public void setInterval(Long interval) {
        this.interval = interval;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getTxHashRoot() {
        return txHashRoot;
    }

    public void setTxHashRoot(String txHashRoot) {
        this.txHashRoot = txHashRoot;
    }

    public String getStatusHashRoot() {
        return statusHashRoot;
    }

    public void setStatusHashRoot(String statusHashRoot) {
        this.statusHashRoot = statusHashRoot;
    }

    public String getResultHashRoot() {
        return resultHashRoot;
    }

    public void setResultHashRoot(String resultHashRoot) {
        this.resultHashRoot = resultHashRoot;
    }
}
