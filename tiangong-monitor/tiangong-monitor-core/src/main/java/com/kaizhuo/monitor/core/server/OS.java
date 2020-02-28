package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 操作系统
 * @author: miaochen
 * @create: 2020-02-28 22:33
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class OS implements Serializable {

    /**
     * hostname
     */
    private String hostName;

    /**
     * 默认ip
     */
    private String defaultIp;

    /**
     * 操作系统类型
     */
    private String family;

    /**
     * 版本
     */
    private String version;

    /**
     * 构建号
     */
    private String buildNumber;

    /**
     * 系统位数
     */
    private int bitness = 0;

    /**
     * 线程数
     */
    private int threadCount = 0;

    /**
     * 进程数
     */
    private int processCount = 0;

    /**
     * 文件系统
     */
    private List<FileStore> fileStores;
}
