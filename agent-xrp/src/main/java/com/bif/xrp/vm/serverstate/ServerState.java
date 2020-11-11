package com.bif.xrp.vm.serverstate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ServerState {
    @JsonProperty("build_version")
    private String buildVersion;
    @JsonProperty("complete_ledgers")
    private String completeLedgers;
    @JsonProperty("io_latency_ms")
    private int ioLatencyMs;
    @JsonProperty("jq_trans_overflow")
    private String jqTransOverflow;
    @JsonProperty("last_close")
    private LastClose lastClose;
    @JsonProperty("load_base")
    private int loadBase;
    @JsonProperty("load_factor")
    private int loadFactor;
    @JsonProperty("load_factor_fee_escalation")
    private int loadFactorFeeEscalation;
    @JsonProperty("load_factor_fee_queue")
    private int loadFactorFeeQueue;
    @JsonProperty("load_factor_fee_reference")
    private int loadFactorFeeReference;
    @JsonProperty("load_factor_server")
    private int loadFactorServer;
    @JsonProperty("peer_disconnects")
    private String peerDisconnects;
    @JsonProperty("peer_disconnects_resources")
    private String peerDisconnectsResources;
    @JsonProperty("peers")
    private int peers;
    @JsonProperty("pubkey_node")
    private String pubkeyNode;
    @JsonProperty("server_state")
    private String serverState;
    @JsonProperty("server_state_duration_us")
    private String serverStateDurationUs;
    @JsonProperty("state_accounting")
    private StateAccounting stateAccounting;
    @JsonProperty("time")
    private String time;
    @JsonProperty("uptime")
    private long uptime;
    @JsonProperty("validated_ledger")
    private ValidatedLedger validatedLedger;
    @JsonProperty("validation_quorum")
    private int validationQuorum;


    public void setBuildVersion(String buildVersion) {
        this.buildVersion = buildVersion;
    }

    public String getBuildVersion() {
        return buildVersion;
    }

    public void setCompleteLedgers(String completeLedgers) {
        this.completeLedgers = completeLedgers;
    }

    public String getCompleteLedgers() {
        return completeLedgers;
    }

    public void setIoLatencyMs(int ioLatencyMs) {
        this.ioLatencyMs = ioLatencyMs;
    }

    public int getIoLatencyMs() {
        return ioLatencyMs;
    }

    public void setJqTransOverflow(String jqTransOverflow) {
        this.jqTransOverflow = jqTransOverflow;
    }

    public String getJqTransOverflow() {
        return jqTransOverflow;
    }

    public void setLastClose(LastClose lastClose) {
        this.lastClose = lastClose;
    }

    public LastClose getLastClose() {
        return lastClose;
    }

    public void setLoadBase(int loadBase) {
        this.loadBase = loadBase;
    }

    public int getLoadBase() {
        return loadBase;
    }

    public void setLoadFactor(int loadFactor) {
        this.loadFactor = loadFactor;
    }

    public int getLoadFactor() {
        return loadFactor;
    }

    public void setLoadFactorFeeEscalation(int loadFactorFeeEscalation) {
        this.loadFactorFeeEscalation = loadFactorFeeEscalation;
    }

    public int getLoadFactorFeeEscalation() {
        return loadFactorFeeEscalation;
    }

    public void setLoadFactorFeeQueue(int loadFactorFeeQueue) {
        this.loadFactorFeeQueue = loadFactorFeeQueue;
    }

    public int getLoadFactorFeeQueue() {
        return loadFactorFeeQueue;
    }

    public void setLoadFactorFeeReference(int loadFactorFeeReference) {
        this.loadFactorFeeReference = loadFactorFeeReference;
    }

    public int getLoadFactorFeeReference() {
        return loadFactorFeeReference;
    }

    public void setLoadFactorServer(int loadFactorServer) {
        this.loadFactorServer = loadFactorServer;
    }

    public int getLoadFactorServer() {
        return loadFactorServer;
    }

    public void setPeerDisconnects(String peerDisconnects) {
        this.peerDisconnects = peerDisconnects;
    }

    public String getPeerDisconnects() {
        return peerDisconnects;
    }

    public void setPeerDisconnectsResources(String peerDisconnectsResources) {
        this.peerDisconnectsResources = peerDisconnectsResources;
    }

    public String getPeerDisconnectsResources() {
        return peerDisconnectsResources;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public int getPeers() {
        return peers;
    }

    public void setPubkeyNode(String pubkeyNode) {
        this.pubkeyNode = pubkeyNode;
    }

    public String getPubkeyNode() {
        return pubkeyNode;
    }

    public void setServerState(String serverState) {
        this.serverState = serverState;
    }

    public String getServerState() {
        return serverState;
    }

    public void setServerStateDurationUs(String serverStateDurationUs) {
        this.serverStateDurationUs = serverStateDurationUs;
    }

    public String getServerStateDurationUs() {
        return serverStateDurationUs;
    }

    public void setStateAccounting(StateAccounting stateAccounting) {
        this.stateAccounting = stateAccounting;
    }

    public StateAccounting getStateAccounting() {
        return stateAccounting;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public long getUptime() {
        return uptime;
    }

    public void setValidatedLedger(ValidatedLedger validatedLedger) {
        this.validatedLedger = validatedLedger;
    }

    public ValidatedLedger getValidatedLedger() {
        return validatedLedger;
    }

    public void setValidationQuorum(int validationQuorum) {
        this.validationQuorum = validationQuorum;
    }

    public int getValidationQuorum() {
        return validationQuorum;
    }

}