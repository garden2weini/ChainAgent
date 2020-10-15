package com.bif.ethereumagent.vm;

import java.io.Serializable;

/**
 * chainId：链ID，唯一标识
 * type：节点类型，101超级节点;102监管节点;103骨干节点;104共识节点;105服务节点 等
 * nodeId：节点ID，唯一标识
 * name：节点名称
 * symbol：节点标识
 * detail：节点描述信息
 * location：地理位置
 * publicIP：IP地址
 * port：端口
 * nodeVersion：节点运行版本
 * os：节点操作系统
 * cpu：CPU配置
 * cpuUsage：CPU利用率
 * memory：内存配置
 * memoryUsage：内存利用率
 * disk：硬盘配置
 * diskUsage：硬盘利用率
 * net：网络配置
 * netUsage：网络利用率
 * IoUsage：网络配置
 * dataSize：节点目的大小
 * tps：tps数
 */
public class NodeInfoVM implements Serializable {

    private static final long serialVersionUID = -2933211184829466564L;

    private String chainId;
    private String type;
    private String nodeId;
    private String name;
    private String symbol;
    private String detail;
    private String state;
    private String province;
    private String city;
    private String publicIP;
    private String port;
    private String nodeVersion;
    private String os;
    private String cpu;
    private String cpuUsage;
    private String memory;
    private String memoryUsage;
    private String disk;
    private String diskUsage;
    private String net;
    private String netUsage;
    private String IoUsage;
    private String dataSize;
    private String tps;


    public String getChainId() {
        return chainId;
    }

    public void setChainId(String chainId) {
        this.chainId = chainId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPublicIP() {
        return publicIP;
    }

    public void setPublicIP(String publicIP) {
        this.publicIP = publicIP;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getNodeVersion() {
        return nodeVersion;
    }

    public void setNodeVersion(String nodeVersion) {
        this.nodeVersion = nodeVersion;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(String cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(String memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(String diskUsage) {
        this.diskUsage = diskUsage;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNetUsage() {
        return netUsage;
    }

    public void setNetUsage(String netUsage) {
        this.netUsage = netUsage;
    }

    public String getIoUsage() {
        return IoUsage;
    }

    public void setIoUsage(String ioUsage) {
        IoUsage = ioUsage;
    }

    public String getDataSize() {
        return dataSize;
    }

    public void setDataSize(String dataSize) {
        this.dataSize = dataSize;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

}
