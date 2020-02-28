package com.kaizhuo.monitor.core.jvm;

import com.kaizhuo.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: Jvm启动
 * @author: miaochen
 * @create: 2020-02-28 21:58
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class JvmStart extends MonitorId {


    private static final long serialVersionUID = 4802435487211509845L;
    /**
     * 进程id
     */
    private Integer pid;
    /**
     * 主函数
     */
    private String mainClass;
    /**
     * jdk版本
     */
    private String javaVersion;
    /**
     * jdk
     */
    private String javaVendor;
    /**
     * java_home
     */
    private String javaHome;

    private int availableProcessors;
    private long totalPhysicalMemorySize;
    private long totalSwapSpaceSize;
    private long startTime;
    private String classPath;
    private String libPath;
    private String bootClassPath;
    private String userDir;
    private String userName;
    private String userHome;
    private String youngGC;
    private String fullGC;
    private String arguments;
    private boolean webConnector = false;
    private Integer webPort;
}
