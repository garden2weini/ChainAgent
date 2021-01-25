package com.bif.eos.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bif.eos.vm.Block;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * EOS接口封装
 * 参考：https://blog.csdn.net/killer_bo/article/details/87877098
 */
public class EosClient {
    private String address;
    RestTemplate restTemplate; // = new RestTemplate();

    // curl http://127.0.0.1:8888/v1/chain/get_info
    private static final String GET_BLOCK = "/v1/chain/get_block";

    private CloseableHttpClient httpClient()
    {
        // 支持HTTP、HTTPS
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(100);
        connectionManager.setValidateAfterInactivity(2000);
        RequestConfig requestConfig = RequestConfig.custom()
                // 服务器返回数据(response)的时间，超时抛出read timeout
                .setSocketTimeout(65000)
                // 连接上服务器(握手成功)的时间，超时抛出connect timeout
                .setConnectTimeout(5000)
                // 从连接池中获取连接的超时时间，超时抛出ConnectionPoolTimeoutException
                .setConnectionRequestTimeout(1000)
                .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager).build();
    }

    public EosClient(String address) {
        this.address = address;
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient());
        this.restTemplate = new RestTemplate(requestFactory);
    }

    /**
     * get_block
     * curl -X POST -H 'Content-Type:application/json' --data '{"block_num_or_id":"138313092"}' https://api-kylin.eosasia.one/v1/chain/get_block
     * @param blockId
     * @return
     */
    public Block getBlock(String blockId){
        String url = this.address + EosClient.GET_BLOCK;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //Map<String, String> values = new HashMap<>();
        JSONObject values = new JSONObject();
        values.put("block_num_or_id",blockId);
        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(values,headers);

        //访问接口并获取返回值
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url,httpEntity, String.class);
        //输出接口所返回过来的值
        Block block = JSON.parseObject(responseEntity.getBody(), Block.class);
        System.out.println("...." + responseEntity.getBody());
        for(int i = 0; i < block.getTransactions().length; i++) {
            System.out.println("...block.transactions:" + block.getTransactions()[i].getCpuUsageUs());
            System.out.println("...block.transactions:" + block.getTransactions()[i].getNetUsageWords());
            System.out.println("...block.transactions:" + block.getTransactions()[i].getStatus());
        }
        return block;
    }
}
