package com.bif.sandbox;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * NOTE：Basic基本上调用不了什么接口
 * 通过coinmarketcap接口获取数据 https://coinmarketcap.com/
 * curl -H "X-CMC_PRO_API_KEY: b1d5c5db-6b2d-4195-bab5-00f788f96c81" -H "Accept: application/json" -d "start=1&limit=5000&convert=USD" -G https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest
 * curl -H "X-CMC_PRO_API_KEY: b1d5c5db-6b2d-4195-bab5-00f788f96c81" -H "Accept: application/json" -d "start=1&limit=5000&convert=CNY" -G https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest
 * (limited)curl -H "X-CMC_PRO_API_KEY: b1d5c5db-6b2d-4195-bab5-00f788f96c81" -H "Accept: application/json" -d "symbol=ETH&convert=USD,CNY&amount=50" -G https://pro-api.coinmarketcap.com/v1/tools/price-conversion
 */
public class CoinMarketDemo {
    static String apiKey = "b1d5c5db-6b2d-4195-bab5-00f788f96c81";

    public static void main(String[] args) {

    }

    public static String cryptocurrencyListingsLatest() {
        String uri = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        List<NameValuePair> paratmers = new ArrayList<NameValuePair>();
        paratmers.add(new BasicNameValuePair("start","1"));
        paratmers.add(new BasicNameValuePair("limit","5000"));
        paratmers.add(new BasicNameValuePair("convert","USD"));

        String result = "";
        try {
            result = makeAPICall(uri, paratmers);
            System.out.println(result);
        } catch (IOException e) {
            System.out.println("Error: cannont access content - " + e.toString());
        } catch (URISyntaxException e) {
            System.out.println("Error: Invalid URL " + e.toString());
        }
        return result;
    }

    public static String makeAPICall(String uri, List<NameValuePair> parameters)
            throws URISyntaxException, IOException {
        String response_content = "";

        URIBuilder query = new URIBuilder(uri);
        query.addParameters(parameters);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet request = new HttpGet(query.build());

        request.setHeader(HttpHeaders.ACCEPT, "application/json");
        request.addHeader("X-CMC_PRO_API_KEY", apiKey);

        CloseableHttpResponse response = client.execute(request);

        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            response_content = EntityUtils.toString(entity);
            EntityUtils.consume(entity);
        } finally {
            response.close();
        }

        return response_content;
    }
}