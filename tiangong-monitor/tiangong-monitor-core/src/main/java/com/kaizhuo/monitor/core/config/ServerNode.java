package com.kaizhuo.monitor.core.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.config
 * @description: TCP服务器节点配置
 * @author: miaochen
 * @create: 2020-02-28 22:04
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class ServerNode implements Serializable {

    private static final long serialVersionUID = -3183938091902989744L;
    /**
     * ip 地址
     */
    public String host;

    /**
     * 端口
     */
    public int port = 6789;

    public ServerNode(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
