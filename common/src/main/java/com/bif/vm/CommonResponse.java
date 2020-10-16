package com.bif.vm;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class CommonResponse<T> implements Serializable {

    @JsonIgnore
    public transient final String SUCCESS_CODE = "200";

    @JsonIgnore
    public transient final String FAILED_CODE = "400";

    @JsonIgnore
    public transient final String ERROR_CODE = "500";

    public String code;

    public String message;

    public T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
