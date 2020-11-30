package com.bif.bcos.diy;

import org.fisco.bcos.web3j.protocol.core.DefaultBlockParameter;

public class BifBlockParameter implements DefaultBlockParameter {
    private String blockNumber;

    public BifBlockParameter(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    @Override
    public String getValue() {
        return blockNumber;
    }
}
