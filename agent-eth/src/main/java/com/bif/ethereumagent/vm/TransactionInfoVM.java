package com.bif.ethereumagent.vm;

import java.io.Serializable;

/**
 * chainId：链ID
 * hash：交易Hash
 * blockId：区块ID
 * blockHash：区块Hash
 * blockHeight：区块高度
 * timestamp：交易时间
 * status：交易状态
 * amount：交易额
 * gasPrice：价格
 * gasLimit：交易允许最大消耗数
 * gasUsed：已消耗数
 * fee：交易费
 * from：转出账户
 * to：转入账户
 * data：扩展字段
 * nonce：交易去重
 * txIndex：交易所在区块位置
 * callContract：调用合约
 * txParameter：交易参数
 * txSignature：交易签名
 * txSize：交易大小
 */
public class TransactionInfoVM implements Serializable {

    private static final long serialVersionUID = 1997854400865424165L;

    /**
     * 与hash意义相同
     */
    private String txHash;

    private String chainId;

    private String hash;

    private String blockHash;

    private Long blockHeight;

    private Long timestamp;

    private Long status;

    private Long amount;

    private String gasPrice;

    private String gasLimit;

    private String gasUsed;

    private Long fee;

    private String from;

    private String to;

    private String data;

    private String nonce;

    private String txIndex;

    private String callContract;

    private String txParameter;

    private String txSignature;

    private Long txSize;

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
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

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public Long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasLimit() {
        return gasLimit;
    }

    public void setGasLimit(String gasLimit) {
        this.gasLimit = gasLimit;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTxIndex() {
        return txIndex;
    }

    public void setTxIndex(String txIndex) {
        this.txIndex = txIndex;
    }

    public String getCallContract() {
        return callContract;
    }

    public void setCallContract(String callContract) {
        this.callContract = callContract;
    }

    public String getTxParameter() {
        return txParameter;
    }

    public void setTxParameter(String txParameter) {
        this.txParameter = txParameter;
    }

    public String getTxSignature() {
        return txSignature;
    }

    public void setTxSignature(String txSignature) {
        this.txSignature = txSignature;
    }

    public Long getTxSize() {
        return txSize;
    }

    public void setTxSize(Long txSize) {
        this.txSize = txSize;
    }
}
