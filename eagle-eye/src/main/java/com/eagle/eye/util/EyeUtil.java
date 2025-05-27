package com.eagle.eye.util;

import lombok.Data;

public class EyeUtil {
    private static final EyeUtil util = new EyeUtil();
    public static EyeUtil getInstance() {
        return util;
    }
    private EyeUtil() {
    }

    public SystemInfo getSystemInfo() {
        SystemInfo info = new SystemInfo();
        info.setCpu(getCpuInfo());
        info.setMemory(getMemoryInfo());
        info.setDisk(getDiskInfo());
        return info;
    }
    private CpuInfo getCpuInfo() {
        CpuInfo info = new CpuInfo();
        info.setCpuName("Intel(R) Core(TM) i7-8700K CPU @ 3.70GHz");
        info.setCpuNum(8);
        info.setCpuUsage(0.0);
        return info;
    }
    private MemoryInfo getMemoryInfo() {
        MemoryInfo info = new MemoryInfo();
        info.setTotal(0.0);
        info.setUsed(0.0);
        info.setFree(0.0);
        return info;
    }
    private DiskInfo getDiskInfo() {
        DiskInfo info = new DiskInfo();
        info.setTotal(0.0);
        info.setUsed(0.0);
        info.setFree(0.0);
        return info;
    }
    @Data
    public static class SystemInfo {
        private CpuInfo cpu;
        private MemoryInfo memory;
        private DiskInfo disk;
    }
    @Data
    public static class CpuInfo {
        private String cpuName;
        private int cpuNum;
        private double cpuUsage;
    }
    @Data
    public static class MemoryInfo {
        private double total;
        private double used;
        private double free;
    }
    @Data
    public static class DiskInfo {
        private double total;
        private double used;
        private double free;
    }
}
