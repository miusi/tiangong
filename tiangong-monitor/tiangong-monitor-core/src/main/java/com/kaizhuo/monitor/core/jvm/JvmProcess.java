package com.kaizhuo.monitor.core.jvm;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: jvm运行
 * @author: miaochen
 * @create: 2020-02-28 22:00
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class JvmProcess implements Serializable {

    private static final long serialVersionUID = -7090861022029671018L;
    private int pid;

    private String mainClass;

    private boolean current;

    public JvmProcess(int pid, String mainClass, boolean current){
        this.pid  = pid;
        this.mainClass = mainClass;
        this.current = current;
    }
}
