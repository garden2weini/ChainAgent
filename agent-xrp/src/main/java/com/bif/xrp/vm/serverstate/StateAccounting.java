
package com.bif.xrp.vm.serverstate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StateAccounting {

    private Connected connected;
    private Disconnected disconnected;
    private Full full;
    private Syncing syncing;
    private Tracking tracking;

    public void setConnected(Connected connected) {
         this.connected = connected;
     }
     public Connected getConnected() {
         return connected;
     }

    public void setDisconnected(Disconnected disconnected) {
         this.disconnected = disconnected;
     }
     public Disconnected getDisconnected() {
         return disconnected;
     }

    public void setFull(Full full) {
         this.full = full;
     }
     public Full getFull() {
         return full;
     }

    public void setSyncing(Syncing syncing) {
         this.syncing = syncing;
     }
     public Syncing getSyncing() {
         return syncing;
     }

    public void setTracking(Tracking tracking) {
         this.tracking = tracking;
     }
     public Tracking getTracking() {
         return tracking;
     }

}