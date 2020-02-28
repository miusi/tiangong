package com.kaizhuo.monitor.agent.handler;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description: 消息发送器
 * @author: miaochen
 * @create: 2020-02-28 22:15
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public interface Sender {
    /**
     * 发送数据
     * @param msg 发送的消息
     * @return
     */
    boolean sendData(String msg);
}
