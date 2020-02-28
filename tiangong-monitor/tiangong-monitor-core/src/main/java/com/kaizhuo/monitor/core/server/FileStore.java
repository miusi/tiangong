package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 文件分区
 * @author: miaochen
 * @create: 2020-02-28 22:30
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class FileStore implements Serializable {


    private static final long serialVersionUID = 5951586284453441827L;
    /**
     * 分区名
     */
    private String name;
    /**
     * 挂载
     */
    private String mount;
    /**
     * 类型
     */
    private String fsType;
    /**
     * 可用空间
     */
    private long usableSpace;
    /**
     * 总空间
     */
    private long totalSpace;
}
