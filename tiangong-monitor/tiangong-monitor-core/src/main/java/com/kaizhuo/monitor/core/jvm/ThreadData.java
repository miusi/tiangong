package com.kaizhuo.monitor.core.jvm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.jvm
 * @description: 线程信息
 * @author: miaochen
 * @create: 2020-02-28 21:53
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ThreadData implements Serializable {


    private static final long serialVersionUID = -1153516415327190647L;

    /**
     * 名称
     */
    private String name;
    /**
     * 线程Id
     */
    private long threadId;

    private int priority;

    /**
     * 是否守护进程
     */
    private boolean daemon;

    private Thread.State state;

    /**
     * cpu耗时
     */
    private long cpuTimeMillis;
    /**
     * 线程使用耗时
     */
    private long userTimeMillis;

    /**
     * 死锁
     */
    private boolean deadlocked;

    /**
     * 全局线程号
     */
    private String globalThreadId;

    /**
     * 栈
     */
    private List<StackTraceElement> stackTrace;

    public ThreadData(Thread thread, List<StackTraceElement> stackTrace, long cpuTimeMillis, long userTimeMillis, boolean deadlocked, String instancecode) {
        this.name = thread.getName();
        this.threadId = thread.getId();
        this.priority = thread.getPriority();
        this.daemon = thread.isDaemon();
        this.state = thread.getState();
        this.cpuTimeMillis = cpuTimeMillis;
        this.userTimeMillis = userTimeMillis;
        this.deadlocked = deadlocked;
        this.globalThreadId = buildGlobalThreadId(thread, instancecode);
    }

    private static String buildGlobalThreadId(Thread thread, String instancecode) {
//        return MonitorUtil.getPid() + "_" + instancecode + "_" + thread.getId();
        return null;
    }
}
