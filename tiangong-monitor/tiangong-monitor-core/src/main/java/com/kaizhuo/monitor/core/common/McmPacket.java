package com.kaizhuo.monitor.core.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.tio.core.intf.Packet;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.common
 * @description: 消息Packet
 * @author: miaochen
 * @create: 2020-02-28 21:35
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class McmPacket extends Packet {

    private static final long serialVersionUID = 5157441532615762677L;

    //消息头的长度
    public static final int HEADER_LENGTH = 4;
    public static final String CHARSET = "utf-8";
    private byte[] body;
}
