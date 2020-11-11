/**
  * Copyright 2020 json.cn 
  */
package com.bif.xrp.vm.serverstate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ValidatedLedger {
    @JsonProperty("base_fee")
    private int baseFee;
    @JsonProperty("close_time")
    private long closeTime;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("reserve_base")
    private long reserveBase;
    @JsonProperty("reserve_inc")
    private long reserveInc;
    @JsonProperty("seq")
    private long seq;

    public void setBaseFee(int baseFee) {
         this.baseFee = baseFee;
     }
     public int getBaseFee() {
         return baseFee;
     }

    public void setCloseTime(long closeTime) {
         this.closeTime = closeTime;
     }
     public long getCloseTime() {
         return closeTime;
     }

    public void setHash(String hash) {
         this.hash = hash;
     }
     public String getHash() {
         return hash;
     }

    public void setReserveBase(long reserveBase) {
         this.reserveBase = reserveBase;
     }
     public long getReserveBase() {
         return reserveBase;
     }

    public void setReserveInc(long reserveInc) {
         this.reserveInc = reserveInc;
     }
     public long getReserveInc() {
         return reserveInc;
     }

    public void setSeq(long seq) {
         this.seq = seq;
     }
     public long getSeq() {
         return seq;
     }

}