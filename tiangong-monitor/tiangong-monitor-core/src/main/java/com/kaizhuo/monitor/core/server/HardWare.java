package com.kaizhuo.monitor.core.server;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.server
 * @description: 硬件信息
 * @author: miaochen
 * @create: 2020-02-28 22:31
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Data
public class HardWare implements Serializable {
    public Cpu cpu;
    public Memory memory;
    public List<Disk> disks;
    public List<NetWork> netWorks;
}
