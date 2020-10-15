package com.bif.ethereumagent.vm;

import java.io.Serializable;

/**
 *     chainId：链ID，唯一标识
 *     type：类型，0：星火主链，1：星火子链，2：联盟链，3：公有链
 *     blockchain：链技术，BIF：星火链，FABRIC：超级账本，XUPERCHIAN：百度超级链 等 （TODO）
 *     pChainId：父链ID，若是主链，则pChainId为""；若是子链，则pChainId为主链的chainId
 *     name：链名称
 *     symbol：标识名称
 *     logo：链Logo标识
 *     consensus：链的共识信息
 *     detail：链的描述信息
 *     website：官网地址
 *     chainType：区块链类型（Fabric、Bcos、CITA、Xuperchain等）
 *     status：状态
 */
public class BlockChainInfoVM implements Serializable {

    private static transient final long serialVersionUID = -87067977257794022L;

    private String chainId;

    private String type;

    private String chainType;

    private String status;

    private String blockchain;

    private String pChainId;

    private String name;

    private String symbol;

    private String logo;

    private String consensus;

    private String detail;

    private String website;

    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }

    public String getpChainId() {
        return pChainId;
    }

    public void setpChainId(String pChainId) {
        this.pChainId = pChainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getConsensus() {
        return consensus;
    }

    public void setConsensus(String consensus) {
        this.consensus = consensus;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getChainType() {
        return chainType;
    }

    public void setChainType(String chainType) {
        this.chainType = chainType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
