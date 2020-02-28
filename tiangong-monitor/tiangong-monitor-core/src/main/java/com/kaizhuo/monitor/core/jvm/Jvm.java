package com.kaizhuo.monitor.core.jvm;

import com.kaizhuo.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: Jvm
 * @author: miaochen
 * @create: 2020-02-28 22:00
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Jvm extends MonitorId {
    private Integer pid;
    private String mainClass;
    private long loadedClassCount;
    private long totalLoadedClassCount;
    private long unloadedClassCount;
    private double processCpuLoad;
    private long processCpuTime;
    private double systemCpuLoad;
    private double systemLoadAverage;
    private long heapCommitted;
    private long heapInit;
    private long heapMax;
    private long heapUsed;
    private long nonHeapCommitted;
    private long nonHeapInit;
    private long nonHeapMax;
    private long nonHeapUsed;
    private int threadCount;
    private int threadDaemonCount;
    private int threadPeakCount;
    private long threadTotalCount;
    private List<JvmGc> gcInfo;
    private List<MemoryPool> memoryPoolList;
    private List<BufferPool> bufferPoolList;
    private List<ThreadData> threadList;
}
