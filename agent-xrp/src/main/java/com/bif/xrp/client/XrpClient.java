package com.bif.xrp.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bif.xrp.vm.LedgerHeader;
import com.bif.xrp.vm.serverstate.ServerState;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Ref: https://xrpl.org/public-rippled-methods.html
 * TOPOLOGY: http://data.ripple.com/v2/network/topology/links
 */
public class XrpClient {
    RestTemplate restTemplate = new RestTemplate();

    private String postUrl;
    private final static String RESULT = "result";
    private final static String SUCCESS = "success";

    // The Ripple Data API v2 provides access to information about changes in the XRP Ledger, including transaction history and processed analytical data.
    private String getUrl = "https://data.ripple.com";

    public XrpClient(String url) {
        this.postUrl = url;
    }

    public static void main(String[] args) {
        XrpClient client = new XrpClient("https://s1.ripple.com:51234");


        JSONObject param = new JSONObject();
        ServerState serverState = client.serverState();
        //System.out.println("ServerState:" + serverState.getServerState() +"\n" + serverState.getCompleteLedgers());
        LedgerHeader ledger;

        Integer curIdx = client.currentLedger();

        for (Integer ledgerIndex = curIdx; ledgerIndex >= 0; ledgerIndex--) {
            ledger = client.ledger(ledgerIndex.toString(), true, false);
            System.out.println("Ledger Index: " + ledgerIndex);

            String[] transHashs = ledger.getTransactions();
            if(transHashs == null) continue;
            for(int i = 0; i< transHashs.length; i++) {
                client.tx(transHashs[i]);
            }
        }

    }

    public LedgerHeader ledger() {
        return ledger(null, false, false);
    }

    /**
     * Retrieve information about the public ledger.
     *
     * @param transactions If true, return information on transactions in the specified ledger version. Defaults to false. Ignored if you did not specify a ledger version.
     * @param queue        If true, and the command is requesting the current ledger, includes an array of queued transactions in the results.
     * @return
     */
    public LedgerHeader ledger(String ledgerIndex, boolean transactions, boolean queue) {
        if (ledgerIndex == null || ledgerIndex.isEmpty()) ledgerIndex = "validated";

        LedgerHeader ledger = new LedgerHeader();
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("ledger_index", ledgerIndex);
        params.put("accounts", false);
        params.put("full", false);
        params.put("transactions", transactions);
        params.put("expand", false);
        params.put("owner_funds", false);
        JSONObject result = doRequest4Json("ledger", params);
        String status = result.getJSONObject(RESULT).getString("status");
        Boolean validated = result.getJSONObject(RESULT).getBoolean("validated");
        if (SUCCESS.equals(status)) {
            String obj = result.getJSONObject(RESULT).getString("ledger");
            ledger = JSON.parseObject(obj, LedgerHeader.class);
        }
        return ledger;
    }

    /**
     * The fee command reports the current state of the open-ledger requirements for the transaction cost.
     * This requires the FeeEscalation amendment to be enabled.
     *
     * @return
     */
    public JSONObject fee() {
        HashMap<String, Object> params = new HashMap<String, Object>();

        JSONObject result = doRequest4Json("fee", params);
        return result;
    }

    public Integer currentLedger() {
        HashMap<String, Object> params = new HashMap<String, Object>();

        JSONObject result = doRequest4Json("ledger_current", params);
        Integer currentIdx = result.getJSONObject(RESULT).getInteger("ledger_current_index");
        String status = result.getJSONObject(RESULT).getString("status");

        if(!SUCCESS.equals(status)) {
            currentIdx = -1;
        }
        return currentIdx;
    }

    /**
     * The ledger_data method retrieves contents of the specified ledger. You can iterate through several calls to retrieve the entire contents of a single ledger version.
     *
     * @return
     */
    public JSONObject ledgerData(String ledgerHash) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("binary", true);
        params.put("ledger_hash", ledgerHash);
        params.put("limit", 4);

        JSONObject result = doRequest4Json("ledger_data", params);
        return result;
    }

    /**
     * The tx method retrieves information on a single transaction.
     *
     * @param transaction
     * @return
     */
    public JSONObject tx(String transaction) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("transaction", transaction);
        params.put("binary", false);

        JSONObject result = doRequest4Json("tx", params);
        return result;
    }

    /**
     * The server_info command asks the server for a human-readable version of various information about the rippled server being queried.
     * NOTE: use server_state interface.
     *
     * @return
     */
    @Deprecated
    public JSONObject serverInfo() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        JSONObject result = doRequest4Json("server_info", params);
        return result;
    }

    /**
     * The server_state command asks the server for various machine-readable information about the rippled server's current state. The response is almost the same as the server_info method, but uses units that are easier to process instead of easier to read.
     */
    public ServerState serverState() {
        ServerState serverState = new ServerState();
        HashMap<String, Object> params = new HashMap<String, Object>();
        JSONObject result = doRequest4Json("server_state", params);

        String status = result.getJSONObject("result").getString("status");
        Boolean validated = result.getJSONObject("result").getBoolean("validated");
        if ("success".equals(status)) {
            String obj = result.getJSONObject("result").getString("state");
            serverState = JSON.parseObject(obj, ServerState.class);
        }
        return serverState;

    }

    public <T> T doRequest(Class<T> clazz, String method, Object... params) {
        String resp = this.doRequest(method, params);
        //输出接口所返回过来的值
        T result = JSON.parseObject(resp, clazz);
        return result;
    }

    private JSONObject doRequest4Json(String method, Object... params) {
        String resp = this.doRequest(method, params);
        System.out.println(resp);
        return JSON.parseObject(resp);
    }

    private String doRequest(String method, Object... params) {
        JSONObject param = new JSONObject();
        param.put("id", System.currentTimeMillis() + "");
        param.put("jsonrpc", "2.0");
        param.put("method", method);
        if (params != null) {
            param.put("params", params);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<JSONObject> httpEntity = new HttpEntity<>(param, headers);
        String resp = "{}";
        try {
            //访问接口并获取返回值
            ResponseEntity<String> responseEntity = restTemplate.postForEntity(postUrl, httpEntity, String.class);
            resp = responseEntity.getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resp;

    }

}