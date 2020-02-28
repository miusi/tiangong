package com.kaizhuo.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.management.MemoryType;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: 内存信息
 * @author: miaochen
 * @create: 2020-02-28 22:01
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class MemoryPool implements Serializable {

    private static final long serialVersionUID = 2635624763370224956L;
    private String memoryPoolName;
    private MemoryType type;
    private long init;
    private long used;
    private long committed;
    private long max;
    private MemoryPool lastMemoryPoolInfo;
}
