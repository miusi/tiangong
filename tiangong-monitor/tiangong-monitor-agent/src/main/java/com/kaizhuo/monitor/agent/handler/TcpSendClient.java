package com.kaizhuo.monitor.agent.handler;

import com.kaizhuo.monitor.core.common.McmPacket;
import lombok.extern.slf4j.Slf4j;
import org.tio.client.intf.ClientAioHandler;
import org.tio.core.ChannelContext;
import org.tio.core.GroupContext;
import org.tio.core.exception.AioDecodeException;
import org.tio.core.intf.Packet;

import java.nio.ByteBuffer;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description: TCP 消息发送客户端
 * @author: miaochen
 * @create: 2020-02-28 23:14
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Slf4j
public class TcpSendClient  implements ClientAioHandler {
    private static McmPacket heartbeatPacket = new McmPacket();

    /**
     * 数据包解码
     */
    @Override
    public McmPacket decode(ByteBuffer buffer, int limit, int position, int readableLength, ChannelContext channelContext) {
        if (readableLength < McmPacket.HEADER_LENGTH) {
            return null;
        }
        int bodyLength = buffer.getInt();
        if (bodyLength < 0) {
            log.error("bodyLength [" + bodyLength + "] is not right, remote:" + channelContext.getClientNode());
        }
        int neededLength = McmPacket.HEADER_LENGTH + bodyLength;
        int isDataEnough = readableLength - neededLength;
        if (isDataEnough < 0) {
            return null;
        } else {
            McmPacket imPacket = new McmPacket();
            if (bodyLength > 0) {
                byte[] dst = new byte[bodyLength];
                buffer.get(dst);
                imPacket.setBody(dst);
            }
            return imPacket;
        }
    }

    /**
     * 数据包编码
     */
    @Override
    public ByteBuffer encode(Packet packet, GroupContext groupContext, ChannelContext channelContext) {
        McmPacket mcmPacket = (McmPacket) packet;
        byte[] body = mcmPacket.getBody();
        int bodyLen = 0;
        if (body != null) {
            bodyLen = body.length;
        }
        int allLen = McmPacket.HEADER_LENGTH + bodyLen;
        ByteBuffer buffer = ByteBuffer.allocate(allLen);
        buffer.order(groupContext.getByteOrder());
        buffer.putInt(bodyLen);
        if (body != null) {
            buffer.put(body);
        }
        return buffer;
    }

    /**
     * 消息处理
     */
    @Override
    public void handler(Packet packet, ChannelContext channelContext) throws Exception {
        McmPacket helloPacket = (McmPacket) packet;
        byte[] body = helloPacket.getBody();
        if (body != null) {
            String str = new String(body, McmPacket.CHARSET);
            System.out.println("收到消息：" + str);
        }
        return;
    }

    @Override
    public McmPacket heartbeatPacket() {
        return heartbeatPacket;
    }



}
