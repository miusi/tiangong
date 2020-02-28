package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: cpu信息
 * @author: miaochen
 * @create: 2020-02-28 22:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Cpu implements Serializable {

    private static final long serialVersionUID = -9082087312217986046L;

    private double lastCpuLoad = 0.0D;
    private long lastCpuLoadTime = 0L;
    private boolean sunMXBean = false;
    /**
     * 逻辑CPU核心数
     */
    protected int logicalProcessorCount = 0;
    /**
     * 物理CPU核心数
     */
    protected int physicalProcessorCount = 0;
    /**
     * CPU packages
     */
    protected int physicalPackageCount = 0;
    private long tickTime;
    private long[] prevTicks;
    private long[] curTicks;
    private long procTickTime;
    private long[][] prevProcTicks;
    private long[][] curProcTicks;
    /**
     * cpu 频率
     */
    private Long cpuVendorFreq;
}
