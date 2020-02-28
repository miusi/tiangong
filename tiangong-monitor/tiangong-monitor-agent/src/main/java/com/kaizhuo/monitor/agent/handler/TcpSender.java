package com.kaizhuo.monitor.agent.handler;

import com.kaizhuo.monitor.core.common.Const;
import com.kaizhuo.monitor.core.common.McmPacket;
import com.kaizhuo.monitor.core.config.Agent;
import lombok.extern.slf4j.Slf4j;
import org.tio.client.ClientChannelContext;
import org.tio.client.ClientGroupContext;
import org.tio.client.ReconnConf;
import org.tio.client.TioClient;
import org.tio.client.intf.ClientAioListener;
import org.tio.core.Node;
import org.tio.core.Tio;

import java.io.IOException;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description: 利用Tcp发送
 * @author: miaochen
 * @create: 2020-02-28 23:13
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
public class TcpSender implements Sender {

    private static volatile TcpSender sender;

    public static TcpSender getInstance() {
        if (sender == null) {
            synchronized (TcpSender.class) {
                if (sender == null) {
                    sender = new TcpSender();
                    sender.init();
                }
            }
        }
        return sender;
    }

    private static TcpSendClient tcpSendClient = new TcpSendClient();
    private static ClientAioListener aioListener = null;
    private static ReconnConf reconnConf = new ReconnConf(5000L);
    private static ClientGroupContext clientGroupContext = new ClientGroupContext(tcpSendClient, aioListener, reconnConf);
    private static TioClient tioClient = null;
    private static ClientChannelContext clientChannelContext = null;

    @Override
    public boolean sendData(String msg) {
        boolean send = true;
        if (msg != null) {
            try {
                Node node = getNode();
                clientChannelContext = tioClient.connect(node);
                McmPacket packet = new McmPacket();
                packet.setBody(msg.getBytes(McmPacket.CHARSET));
                Tio.send(clientChannelContext, packet);
            } catch (Exception e) {
                send = false;
                log.error("发送消息失败", e);
            }
        }
        return send;
    }

    private void init() {
        try {
            clientGroupContext.setHeartbeatTimeout(Const.TIMEOUT);
            tioClient = new TioClient(clientGroupContext);
        } catch (IOException e) {
            log.error("初始化错误", e);
        }
    }

    /**
     * 获取接收消息的服务器
     *
     * @return 服务器配置
     */
    private Node getNode() {
        int nodesSize = Agent.getInstance().nodes.size();
        int random = (int) (1 + Math.random() * nodesSize) - 1;
        Node node = new Node(Agent.getInstance().nodes.get(random).host, Agent.getInstance().nodes.get(random).port);
        return node;
    }
}
