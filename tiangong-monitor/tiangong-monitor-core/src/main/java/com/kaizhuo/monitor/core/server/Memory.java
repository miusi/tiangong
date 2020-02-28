package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 内存
 * @author: miaochen
 * @create: 2020-02-28 22:31
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Memory implements Serializable {

    private static final long serialVersionUID = -4666279277819440805L;
    private long memTotal = 0L;
    private long memAvailable = 0L;
    private long swapTotal = 0L;
    private long swapUsed = 0L;
    private long swapPagesIn = 0L;
    private long swapPagesOut = 0L;
    private long pageSize = 0L;
}
