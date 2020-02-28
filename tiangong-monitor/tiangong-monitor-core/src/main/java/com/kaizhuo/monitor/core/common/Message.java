package com.kaizhuo.monitor.core.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.common
 * @description: 消息主体构成
 * @author: miaochen
 * @create: 2020-02-28 21:37
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Message implements Serializable {

    private static final long serialVersionUID = -2528031741239072062L;

    /**
     * 类
     */
    private String clazz;

    /**
     * 消息内容
     */
    private String data;

    /**
     * 时间戳
     */
    private long timeStamp=System.currentTimeMillis();

    public Message(String clazz, String data) {
        this.clazz = clazz;
        this.data = data;
    }
}
