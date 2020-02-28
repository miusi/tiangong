package com.kaizhuo.monitor.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.common
 * @description: 应用Id
 * @author: miaochen
 * @create: 2020-02-28 21:39
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class MonitorId implements Serializable {

    private static final long serialVersionUID = -3937028349083250298L;

    /**
     * 分组编码
     */
    private String groupKey;

    /**
     * 应用编码
     */
    private String appKey;

    /**
     * host
     */
    private String hostname;

    /**
     * 实例编码
     */
    private String instanceCode;

    public MonitorId() {
    }

    public MonitorId(String groupKey, String appKey) {
        this.groupKey = groupKey;
        this.appKey = appKey;
    }
}
