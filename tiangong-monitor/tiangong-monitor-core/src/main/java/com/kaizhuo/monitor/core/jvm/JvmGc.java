package com.kaizhuo.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: Gc 信息
 * @author: miaochen
 * @create: 2020-02-28 21:51
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class JvmGc implements Serializable {

    private static final long serialVersionUID = -4509290603994951950L;

    /**
     * GC名称
     */
    private String gcName;

    /**
     * GC次数
     */
    private long gcTotalCount;

    /**
     * GC占用时间
     */
    private long gcTotalTime;
}
