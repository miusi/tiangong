package com.kaizhuo.monitor.core.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.config
 * @description: agent配置
 * @author: miaochen
 * @create: 2020-02-28 22:03
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Agent implements Serializable {

    private static final long serialVersionUID = -326247679034279588L;
    private static volatile Agent agent;

    public static Agent getInstance() {
        if (agent == null) {
            synchronized (Agent.class) {
                if (agent == null) {
                    agent = new Agent();
                }
            }
        }
        return agent;
    }

    /**
     * 应用分组
     */
    public String groupKey = null;

    /**
     * 应用key
     */
    public String appKey = null;

    /**
     * Server收集间隔
     */
    public Integer serverRate = 20;

    /**
     * JVM收集间隔
     */
    public Integer jvmRate = 20;

    /**
     * JVM START收集间隔
     */
    public Integer jvmStartRate = 3600;

    /**
     * 心跳间隔
     */
    public Integer heartBeatRate = 5;

    /**
     * 数据发送间隔
     */
    public Integer sendRate = 5;

    /**
     * TCP 服务器节点
     */
    public List<ServerNode> nodes;

    /**
     * kafka topic
     */
    public String topic;
}
