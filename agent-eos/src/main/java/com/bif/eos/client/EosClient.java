package com.bif.eos.client;

import com.alibaba.fastjson.JSON;
import com.bif.eos.vm.Block;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * EOS接口封装
 */
public class EosClient {
    private String address;
    RestTemplate restTemplate = new RestTemplate();
    private static final String GET_BLOCK = "/v1/chain/get_block";

    public EosClient(String address) {
        this.address = address;
    }

    /**
     * get_block
     * @param blockId
     * @return
     */
    public Block getBlock(String blockId){
        String url = this.address + EosClient.GET_BLOCK;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> values = new HashMap<>();
        values.put("block_num_or_id",blockId);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(values,headers);

        //访问接口并获取返回值
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity, String.class);
        //输出接口所返回过来的值
        Block block = JSON.parseObject(responseEntity.getBody(), Block.class);
        /*
        System.out.println("...." + responseEntity.getBody());
        for(int i = 0; i < block.getTransactions().length; i++) {
            System.out.println("...block.transactions:" + block.getTransactions()[i].getCpuUsageUs());
            System.out.println("...block.transactions:" + block.getTransactions()[i].getNetUsageWords());
            System.out.println("...block.transactions:" + block.getTransactions()[i].getStatus());
        }*/
        return block;
    }
}
