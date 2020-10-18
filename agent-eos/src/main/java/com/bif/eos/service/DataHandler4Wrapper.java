package com.bif.eos.service;

import client.EosApiRestClient;
import client.domain.response.chain.Block;
import client.domain.response.chain.ChainInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Try to use eos-java-rpc-wrapper.
 * <dependency>
 *  <groupId>com.github.EOSEssentials</groupId>
 *  <artifactId>eos-java-rpc-wrapper</artifactId>
 *  <version>master</version>
 * </dependency>
 */
@Component
@Deprecated
public class DataHandler4Wrapper {
    @Autowired
    EosApiRestClient eosApiRestClient;

    private void testRpcWrapper() {
        ChainInfo chainInfo = this.eosApiRestClient.getChainInfo();
        System.out.println("ChainInfo:" + chainInfo);
        /* Get the head block */
        Block block = eosApiRestClient.getBlock(eosApiRestClient.getChainInfo().getHeadBlockId());
        System.out.println("HeadBlock:" + block);
    }
}
