package com.bif.ethereumagent.vm;

public class Response {
    public String messageId;
    public String status;
    public String data;

    public Response(String messageId, String status, String data) {
        this.messageId = messageId;
        this.status = status;
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"messageId\":\"" + messageId +
                "\",\"status\":\"" + status +
                "\",\"data\":\"" + data + "\"}";
    }
}
