package com.bif.eos.vm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionReceipt {

    @JsonProperty("net_usage_words")
    private String netUsageWords;

    @JsonProperty("trx")
    private String trx;

    @JsonProperty("cpu_usage_us")
    private String cpuUsageUs;

    @JsonProperty("status")
    private String status;

    public String getNetUsageWords() {
        return netUsageWords;
    }

    public void setNetUsageWords(String netUsageWords) {
        this.netUsageWords = netUsageWords;
    }

    public String getTrx() {
        return trx;
    }

    public void setTrx(String trx) {
        this.trx = trx;
    }

    public String getCpuUsageUs() {
        return cpuUsageUs;
    }

    public void setCpuUsageUs(String cpuUsageUs) {
        this.cpuUsageUs = cpuUsageUs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
