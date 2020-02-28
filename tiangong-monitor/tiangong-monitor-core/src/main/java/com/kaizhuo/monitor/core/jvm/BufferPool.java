package com.kaizhuo.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 22:02
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class BufferPool implements Serializable {

    private static final long serialVersionUID = -9070797549348118690L;
    private Long totalCapacity;
    private Long memoryUsed;
    private Long count;
    private String poolName;
}
