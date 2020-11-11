
package com.bif.xrp.vm.serverstate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Syncing {

    private String duration_us;
    private int transitions;
    public void setDuration_us(String duration_us) {
         this.duration_us = duration_us;
     }
     public String getDuration_us() {
         return duration_us;
     }

    public void setTransitions(int transitions) {
         this.transitions = transitions;
     }
     public int getTransitions() {
         return transitions;
     }

}