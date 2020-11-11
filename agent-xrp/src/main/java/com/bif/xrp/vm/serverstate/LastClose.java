
package com.bif.xrp.vm.serverstate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LastClose {
    @JsonProperty("converge_time")
    private int convergeTime;
    @JsonProperty("proposers")
    private int proposers;

    public void setConvergeTime(int convergeTime) {
         this.convergeTime = convergeTime;
     }
     public int getConvergeTime() {
         return convergeTime;
     }

    public void setProposers(int proposers) {
         this.proposers = proposers;
     }
     public int getProposers() {
         return proposers;
     }

}