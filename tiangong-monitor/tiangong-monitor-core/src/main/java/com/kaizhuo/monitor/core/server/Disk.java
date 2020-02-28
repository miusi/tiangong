package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 磁盘信息
 * @author: miaochen
 * @create: 2020-02-28 22:29
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Disk implements Serializable {

    private static final long serialVersionUID = -4198363697871529623L;

    private long size = 0L;
    private long reads = 0L;
    private long readBytes = 0L;
    private long writes = 0L;
    private long writeBytes = 0L;
    private long currentQueueLength = 0L;
    private long transferTime = 0L;
}
