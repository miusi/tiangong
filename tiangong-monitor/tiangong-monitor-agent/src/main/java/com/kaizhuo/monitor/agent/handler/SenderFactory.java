package com.kaizhuo.monitor.agent.handler;

import cn.hutool.core.util.StrUtil;
import com.kaizhuo.monitor.core.config.Agent;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description:
 * @author: miaochen
 * @create: 2020-02-28 23:16
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class SenderFactory {
    public static Sender getSender() {
        Sender sender = null;
        if (StrUtil.isNotBlank(Agent.getInstance().topic)) {
            // kafka
            sender = KafkaSender.getInstance();
        } else {
            // tpc
            sender = TcpSender.getInstance();
        }
        return sender;
    }
}
