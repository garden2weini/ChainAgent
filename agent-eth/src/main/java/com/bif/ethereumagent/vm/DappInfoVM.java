package com.bif.ethereumagent.vm;

import java.io.Serializable;
import java.util.List;

/**
 *     name：Dapp名称
 *     symbol：Dapp标识
 *     logo：Dapp logo
 *     website：Dapp官网
 *     detail：Dapp描述
 *     userNumber：使用用户量
 *     scenariosType：使用场景
 *     region：覆盖地区
 *     industry：覆盖行业
 *     company：参与企业
 *     timestamp：时间戳
 *     minuteTxCount：分钟交易数
 *     minuteUserCount：分钟用户数
 *     totalAmount：总交易额
 *     totalCount：总交易数
 *     accountBalance：账户余额
 *     callNum：调用合约量
 *     belongChain：代币所在链
 *     dappEvaluation：Dapp评价
 *     userEvaluation：用户评价
 *     intro：Dapp介绍
 *     token：代币合约
 */
public class DappInfoVM implements Serializable {

    private static final long serialVersionUID = 1859662254246278493L;

    private String chainId;

    private String name;

    private String symbol;

    private String minuteTxCount;

    private String minuteUserCount;

    private String logo;

    private String timestamp;

    private String website;

    private String detail;

    private Long userNumber;

    private List<String> scenariosType;

    private List<String> region;

    private List<String> industry;

    private List<String> company;

    private Long totalAmount;

    private Long totalCount;

    private Long accountBalance;

    private Long callNum;

    private String belongChain;

    private Long dappEvaluation;

    private Long userEvaluation;

    private String intro;

    private String token;

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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMinuteTxCount() {
        return minuteTxCount;
    }

    public void setMinuteTxCount(String minuteTxCount) {
        this.minuteTxCount = minuteTxCount;
    }

    public String getMinuteUserCount() {
        return minuteUserCount;
    }

    public void setMinuteUserCount(String minuteUserCount) {
        this.minuteUserCount = minuteUserCount;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(Long userNumber) {
        this.userNumber = userNumber;
    }

    public List<String> getScenariosType() {
        return scenariosType;
    }

    public void setScenariosType(List<String> scenariosType) {
        this.scenariosType = scenariosType;
    }

    public List<String> getRegion() {
        return region;
    }

    public void setRegion(List<String> region) {
        this.region = region;
    }

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }

    public List<String> getCompany() {
        return company;
    }

    public void setCompany(List<String> company) {
        this.company = company;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Long accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getCallNum() {
        return callNum;
    }

    public void setCallNum(Long callNum) {
        this.callNum = callNum;
    }

    public String getBelongChain() {
        return belongChain;
    }

    public void setBelongChain(String belongChain) {
        this.belongChain = belongChain;
    }

    public Long getDappEvaluation() {
        return dappEvaluation;
    }

    public void setDappEvaluation(Long dappEvaluation) {
        this.dappEvaluation = dappEvaluation;
    }

    public Long getUserEvaluation() {
        return userEvaluation;
    }

    public void setUserEvaluation(Long userEvaluation) {
        this.userEvaluation = userEvaluation;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
