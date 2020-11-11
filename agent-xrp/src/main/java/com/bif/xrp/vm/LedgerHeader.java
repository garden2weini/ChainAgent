package com.bif.xrp.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Every ledger version has a unique header that describes the contents.
 * You can look up a ledger's header information with the ledger method.
 * NOTE：The following fields are deprecated and may be removed without further notice: accepted, hash (use ledger_hash instead), seqNum (use ledger_index instead), totalCoins (use total_coins instead).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LedgerHeader {

    //@JsonProperty("accepted")
    //private Boolean accepted;
    @JsonProperty("account_hash")
    private String accountHash;
    @JsonProperty("close_flags")
    private Integer closeFlags;
    @JsonProperty("close_time")
    private Integer closeTime;
    @JsonProperty("close_time_human")
    private String closeTimeHuman;
    @JsonProperty("close_time_resolution")
    private Integer closeTimeResolution;
    @JsonProperty("closed")
    private Boolean closed;
    //@JsonProperty("hash")
    //private String hash;
    @JsonProperty("ledger_hash")
    private String ledgerHash;
    @JsonProperty("ledger_index")
    private Integer ledgerIndex;
    @JsonProperty("parent_close_time")
    private Integer parentCloseTime;
    @JsonProperty("parent_hash")
    private String parentHash;
    //@JsonProperty("seqNum")
    //private String seqNum;
    //@JsonProperty("totalCoins")
    //private String totalCoins;
    @JsonProperty("total_coins")
    private String totalCoins;
    @JsonProperty("transaction_hash")
    private String transactionHash;

    @JsonProperty("transactions")
    private String[] transactions;


    public String[] getTransactions() {
        return transactions;
    }

    public void setTransactions(String[] transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        if(this.ledgerHash.length()>0) {
            return this.ledgerHash + "......";
        } else {
            return "Error: ledger is empty!";
        }
    }

    public String getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(String accountHash) {
        this.accountHash = accountHash;
    }

    public Integer getCloseFlags() {
        return closeFlags;
    }

    public void setCloseFlags(Integer closeFlags) {
        this.closeFlags = closeFlags;
    }

    public Integer getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Integer closeTime) {
        this.closeTime = closeTime;
    }

    public String getCloseTimeHuman() {
        return closeTimeHuman;
    }

    public void setCloseTimeHuman(String closeTimeHuman) {
        this.closeTimeHuman = closeTimeHuman;
    }

    public Integer getCloseTimeResolution() {
        return closeTimeResolution;
    }

    public void setCloseTimeResolution(Integer closeTimeResolution) {
        this.closeTimeResolution = closeTimeResolution;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public String getLedgerHash() {
        return ledgerHash;
    }

    public void setLedgerHash(String ledgerHash) {
        this.ledgerHash = ledgerHash;
    }

    public Integer getLedgerIndex() {
        return ledgerIndex;
    }

    public void setLedgerIndex(Integer ledgerIndex) {
        this.ledgerIndex = ledgerIndex;
    }

    public Integer getParentCloseTime() {
        return parentCloseTime;
    }

    public void setParentCloseTime(Integer parentCloseTime) {
        this.parentCloseTime = parentCloseTime;
    }

    public String getParentHash() {
        return parentHash;
    }

    public void setParentHash(String parentHash) {
        this.parentHash = parentHash;
    }

    public String getTotalCoins() {
        return totalCoins;
    }

    public void setTotalCoins(String totalCoins) {
        this.totalCoins = totalCoins;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }


}
