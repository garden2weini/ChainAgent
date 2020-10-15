package com.bif.util;//package com.bif.ethereumagent.util;
//
//import org.apache.commons.io.FileUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import oshi.SystemInfo;
//import oshi.hardware.CentralProcessor;
//import oshi.hardware.HWDiskStore;
//import oshi.hardware.HardwareAbstractionLayer;
//import oshi.hardware.NetworkIF;
//import oshi.software.os.OSFileStore;
//import oshi.software.os.OperatingSystem;
//import oshi.util.FormatUtil;
//import reactor.util.function.Tuple2;
//import reactor.util.function.Tuples;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static java.lang.Thread.sleep;
//
//
//public class NodeMonitorUtil {
//
//    private static final Logger log = LoggerFactory.getLogger(NodeMonitorUtil.class);
//
//    private static final String MICROSOFT = "microsoft";
//    private static final String APPLE = "apple";
//    private static final String LINUX = "linux";
//    private static final String LINUX_NET_NAME = "eth0";
//
//    private static SystemInfo systemInfo = new SystemInfo();
//
//    private static HardwareAbstractionLayer hardware = systemInfo.getHardware();
//    private static OperatingSystem os = systemInfo.getOperatingSystem();
//
//    public static String getPublicIp() {
//        log.info("system monitor : public ip");
//        Optional<NetworkIF> first = hardware.getNetworkIFs().stream().filter(networkIF -> LINUX_NET_NAME.equals(networkIF.getName())).findFirst();
//        if (!first.isPresent()){
//            return null;
//        }
//        return first.get().getIPv4addr()[0];
//    }
//
//    public static String getOS(){
//        log.info("system monitor : os");
//        return os.toString();
//    }
//
//    public static String getCpu(){
//        log.info("system monitor : cpu");
//        CentralProcessor processor = hardware.getProcessor();
//        StringBuilder sb = new StringBuilder(processor.getProcessorIdentifier().getName());
//        sb.append(" ").append(processor.getPhysicalProcessorCount()).append(" physical CPU core(s)");
//        sb.append(" ").append(processor.getLogicalProcessorCount()).append(" logical CPU(s)");
//        return sb.toString();
//    }
//
//    public static String cpuUsage() {
//        log.info("system monitor : cpu usage");
//        CentralProcessor processor = hardware.getProcessor();
//        long[] prevTicks = processor.getSystemCpuLoadTicks();
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        long[] ticks = processor.getSystemCpuLoadTicks();
//        long idleCpu = ticks[CentralProcessor.TickType.IDLE.getIndex()] - prevTicks[CentralProcessor.TickType.IDLE.getIndex()];
//        long totalCpu = Arrays.stream(ticks).sum() - Arrays.stream(prevTicks).sum();
//        return new DecimalFormat("#.##%").format(1.0 - (idleCpu*1.0/totalCpu));
//    }
//
//    public static String getMemory(){
//        log.info("system monitor : memory");
//        return FormatUtil.formatBytes(hardware.getMemory().getTotal());
//    }
//
//    public static String getMemoryUsage() {
//        log.info("system monitor : memory usage");
//        return FormatUtil.formatBytes(hardware.getMemory().getTotal()-hardware.getMemory().getAvailable());
//    }
//
//    public static String getDisk() {
//        log.info("system monitor : disk");
//        ArrayList<HWDiskStore> disks = hardware.getDiskStores().stream().filter(hwDiskStore -> hwDiskStore.getTransferTime()!=0).collect(Collectors.toCollection(ArrayList::new));
//        long total = disks.stream().map(HWDiskStore::getSize).reduce(Long::sum).get();
//        return FormatUtil.formatBytesDecimal(total);
//    }
//
//    public static String getDiskUsage(){
//        log.info("system monitor : disk usage");
//        List<OSFileStore> fileStores = os.getFileSystem().getFileStores(true);
//        fileStores.stream().forEach(osFileStore -> log.debug(osFileStore.toString()));
//
//        Long total = fileStores.stream().map(OSFileStore::getTotalSpace).reduce(Long::sum).get();
//        Long totalFree = fileStores.stream().map(OSFileStore::getFreeSpace).reduce(Long::sum).get();
//        return FormatUtil.formatBytesDecimal(total-totalFree);
//    }
//
//    public static String getNet(){
//        log.info("system monitor : net");
//        return os.getNetworkParams().toString();
//    }
//
//    public static String getNetUsage(){
//        log.info("system monitor : net usage");
//        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
//        Long bytesRecv = networkIFs.stream().map(NetworkIF::getBytesRecv).reduce(Long::sum).get();
//        Long bytesSent = networkIFs.stream().map(NetworkIF::getBytesSent).reduce(Long::sum).get();
//        Long packageRecv = networkIFs.stream().map(NetworkIF::getPacketsRecv).reduce(Long::sum).get();
//        Long packageSent = networkIFs.stream().map(NetworkIF::getPacketsSent).reduce(Long::sum).get();
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("Networks: ").append("packets : ").append(packageRecv).append("/").append(FormatUtil.formatBytes(bytesRecv)).append(" received, ").append(packageSent).append("/").append(FormatUtil.formatBytes(bytesSent)).append(" sent.");
//        return sb.toString();
//    }
//
//    /**
//     *
//     * @return transdata per second , of unit KiByte
//     * @throws IOException
//     */
//    public static String getIOUsage() throws IOException {
//        log.info("system monitor : io usage");
//        return getIOTpsAndTransDataPs().getT2();
//    }
//
//    public static String getDataSize(String chainpath){
//        log.info("system monitor : data size");
//        File file = new File("chainpath");
//        long size = FileUtils.sizeOfDirectory(file);
//        return FormatUtil.formatBytes(size);
//    }
//
//    public static String getTps() throws IOException {
//        log.info("system monitor : tps");
//        return getIOTpsAndTransDataPs().getT1();
//    }
//
//    private static Tuple2<String,String> getIOTpsAndTransDataPs() throws IOException {
//        int dataline = 0;
//        BigDecimal tps = BigDecimal.ZERO;
//        BigDecimal transDataPs = BigDecimal.ZERO;
//        String manufacturer = os.getManufacturer();
//        if (manufacturer.toLowerCase().contains(MICROSOFT)) {
//            return null;
//        }
//        if (manufacturer.toLowerCase().contains(APPLE)){
//            dataline = 2;
//        } else if (manufacturer.toLowerCase().contains(LINUX)){
//            dataline = 3;
//        }
//        Process pro = null;
//        Runtime r = Runtime.getRuntime();
//        String command = "iostat -d";
//        pro = r.exec(command);
//        BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
//        String line = null;
//        int count =  0;
//        StringBuffer sb = new StringBuffer();
//        while((line=in.readLine()) != null){
//            if(++count > dataline){
//                String[] temp = line.trim().split("\\s+");
//                Arrays.stream(temp).forEach(s -> log.debug("\""+s+"\"\t"));
//                if(temp.length > 1 && manufacturer.toLowerCase().contains("apple")){
//                    tps = BigDecimal.valueOf(Double.parseDouble(temp[1]));
//                    transDataPs = BigDecimal.valueOf(Double.parseDouble(temp[0])).multiply(tps);
//                    log.debug(sb.append("IOUsage: ").append(temp[1]).append(" tps, ").append(temp[0]).append(" KB/t, ").toString());
//                }
//                if (temp.length > 1 && manufacturer.toLowerCase().contains("linux")){
//                    tps = BigDecimal.valueOf(Double.parseDouble(temp[1]));
//                    transDataPs = BigDecimal.valueOf(Double.valueOf(temp[2])).add(BigDecimal.valueOf(Double.valueOf(temp[3])));
//                    log.debug(sb.append("IOUsage: ").append(temp[1]).append(" tps, ").append(temp[2]).append(" KB_read/s, ").append(temp[3]).append(" KB_wrtn/s, ").toString());
//                }
//            }
//        }
//        in.close();
//        pro.destroy();
//        return Tuples.of(tps.toString(),transDataPs.toString());
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        System.out.println("os info:\n"+getOS());
//        System.out.println("disk info:\n"+getDisk());
//        System.out.println("cpu info:\n"+getCpu());
//        System.out.println("disk usage info :\n"+getDiskUsage());
//        System.out.println("memory info :\n"+getMemory());
//        System.out.println("memory usage info :\n"+getMemoryUsage());
//        System.out.println("net info :\n"+getNet());
//        System.out.println("net usage info:\n"+getNetUsage());
//        System.out.println("io usage :\n"+getIOUsage());
//        System.out.println("tps :\n"+getTps());
//
//    }
//}
