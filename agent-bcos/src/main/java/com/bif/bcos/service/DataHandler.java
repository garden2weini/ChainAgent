package com.bif.bcos.service;

import org.fisco.bcos.web3j.protocol.Web3j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 注意：推荐使用OracleJDK！！！
 */
@Component
public class DataHandler {
    private static final Logger logger = LoggerFactory.getLogger(DataHandler.class);

    @Autowired
    private Web3j web3j;

    @Scheduled(fixedDelay = 6000_000, initialDelay = 1_000)
    private void tmpBaseLogical() {
        //通过Web3j对象调用API接口getBlockNumber
        BigInteger blockNumber = null;
        try {
            blockNumber = web3j.getBlockNumber().send().getBlockNumber();
            System.out.println("web3j.getBlockNumber().getId()=" + blockNumber);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
