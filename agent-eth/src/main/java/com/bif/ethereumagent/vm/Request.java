package com.bif.ethereumagent.vm;

import java.io.Serializable;

public class Request implements Serializable {
    public String messageId;
    public String chainType;
    public String chainId;
    public String nodeKey;
    public String dataType;
    public String method;
    public String data;

    public Request(String messageId, String chainType, String chainId, String nodeKey, String dataType, String method, String data) {
        this.messageId = messageId;
        this.chainType = chainType;
        this.chainId = chainId;
        this.nodeKey = nodeKey;
        this.dataType = dataType;
        this.method = method;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"messageId\":\"" + messageId +
                "\",\"chainType\":\"" + chainType +
                "\",\"chainId\":\"" + chainId +
                "\",\"nodeKey\":\"" + nodeKey +
                "\",\"dataType\":\"" + dataType +
                "\",\"method\":\"" + method +
                "\",\"data\":\"" + data + "\"}";
    }
}
