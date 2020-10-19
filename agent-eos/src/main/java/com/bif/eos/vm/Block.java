package com.bif.eos.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Block extends io.eblock.eos4j.api.vo.Block {

    public TransactionReceipt[] getTransactions() {
        return transactions;
    }

    public void setTransactions(TransactionReceipt[] transactions) {
        this.transactions = transactions;
    }

    @JsonProperty("transactions")
    private TransactionReceipt[] transactions;
}
