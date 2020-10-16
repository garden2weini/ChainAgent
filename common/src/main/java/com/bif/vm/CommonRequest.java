package com.bif.vm;

import java.io.Serializable;

public class CommonRequest<T> implements Serializable {

    public String nodeId;
    public String chainName;
    public String event;
    public String accessToken;
    public T params;

    public CommonRequest() {
    }

    public CommonRequest(String nodeId, String accessToken) {
        this.nodeId = nodeId;
        this.accessToken = accessToken;
    }

    public CommonRequest(String nodeId, String chainName,String event, T params) {
        this.nodeId = nodeId;
        this.chainName = chainName;
        this.event = event;
        this.params = params;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public T getParams() {
        return params;
    }

    public void setParams(T params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "CommonRequest{" +
                "nodeId='" + nodeId + '\'' +
                "chainName='" + chainName + '\'' +
                ", event='" + event + '\'' +
                ", params=" + params +
                '}';
    }
}
