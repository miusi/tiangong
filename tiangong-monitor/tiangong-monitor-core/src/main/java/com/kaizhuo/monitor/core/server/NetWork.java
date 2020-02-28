package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 网络
 * @author: miaochen
 * @create: 2020-02-28 22:32
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class NetWork implements Serializable {
    /**
     * mac地址
     */
    private String mac;
    /**
     * ipv4
     */
    private String[] ipv4;
    /**
     * ipv6
     */
    private String[] ipv6;
    /**
     * 入网流量
     */
    private long bytesRecv;
    /**
     * 出网流量
     */
    private long bytesSent;
    /**
     * 入网包
     */
    private long packetsRecv;
    /**
     * 出网包
     */
    private long packetsSent;
    /**
     * 入网丢包
     */
    private long inErrors;
    /**
     * 出网丢包
     */
    private long outErrors;
    /**
     * 传输速度
     */
    private long speed;
}
