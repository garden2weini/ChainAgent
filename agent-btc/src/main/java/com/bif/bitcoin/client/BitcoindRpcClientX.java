package com.bif.bitcoin.client;

import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface BitcoindRpcClientX extends BitcoindRpcClient {

    public ArrayList getNodeAddresses();
    public Long getDifficultyX();
    public LinkedHashMap getMemoryInfo();
    public LinkedHashMap getChainTxStats();
    public BitcoindRpcClient.RawTransaction getRawTransactionX(String txId, String blockHash);
}
