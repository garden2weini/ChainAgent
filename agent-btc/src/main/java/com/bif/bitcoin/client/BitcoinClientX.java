package com.bif.bitcoin.client;

import wf.bitcoin.javabitcoindrpcclient.BitcoinJSONRPCClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BitcoinClientX extends BitcoinJSONRPCClient {
    public BitcoinClientX(String rpcUrl) throws MalformedURLException {
        super(new URL(rpcUrl));
    }

    public ArrayList getNodeAddresses() {
        return (ArrayList) query("getnodeaddresses");
    }

    public LinkedHashMap getMemoryInfo() {
        return (LinkedHashMap) query("getmemoryinfo");
    }

    public LinkedHashMap getChainTxStats() {
        return (LinkedHashMap) query("getchaintxstats");
    }
}
