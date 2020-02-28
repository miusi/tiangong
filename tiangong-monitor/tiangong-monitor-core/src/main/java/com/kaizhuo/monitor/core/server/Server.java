package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 服务器
 * @author: miaochen
 * @create: 2020-02-28 22:33
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class Server implements Serializable {

    private static final long serialVersionUID = -1477395618127649022L;
    private HardWare hardWare;

    private OS os;
}
