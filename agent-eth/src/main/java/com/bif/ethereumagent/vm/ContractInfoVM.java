package com.bif.ethereumagent.vm;

import java.io.Serializable;

/**
 * 合约信息
 * chainId：链ID
 * name：名称
 * contractId：合约ID
 * contractVersion：合约版本
 * address：地址
 * detail：描述
 * txHash：交易Hash
 * txCount：交易数
 * timestamp:交易时间
 * type：类型，0新增；1升级；2删除；3调用
 * blockHash：区块Hash
 * blockHeight：区块高度
 * amount：金额
 * data：扩展字段
 */
public class ContractInfoVM implements Serializable {

    private static final long serialVersionUID = 1414223075272582031L;

    private String chainId;

    private String name;

    private String contractId;

    private String contractVersion;

    private String address;

    private String detail;

    private String txHash;

    private Long txCount;

    private String timestamp;

    private String type;

    private String blockHash;

    private Long blockHeight;

    private Long amount;

    private String data;

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(String contractVersion) {
        this.contractVersion = contractVersion;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public Long getTxCount() {
        return txCount;
    }

    public void setTxCount(Long txCount) {
        this.txCount = txCount;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
