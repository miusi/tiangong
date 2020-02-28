package com.kaizhuo.monitor.agent.handler;

import com.kaizhuo.monitor.agent.util.JvmUtil;
import com.kaizhuo.monitor.agent.util.ServerUtil;
import com.kaizhuo.monitor.core.alarm.Alarm;
import com.kaizhuo.monitor.core.common.Message;
import com.kaizhuo.monitor.core.common.MonitorId;
import com.kaizhuo.monitor.core.config.Agent;
import com.kaizhuo.monitor.core.jvm.Jvm;
import com.kaizhuo.monitor.core.jvm.JvmProcess;
import com.kaizhuo.monitor.core.jvm.JvmStart;
import com.kaizhuo.monitor.core.metric.Metric;
import com.kaizhuo.monitor.core.server.Server;
import com.kaizhuo.monitor.core.util.SerializeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description: 数据收集
 * @author: miaochen
 * @create: 2020-02-28 22:16
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class Collector {
    private static final int MAX_LENGTH=1000000;
    private static BlockingQueue<Message> messageCache=new ArrayBlockingQueue<Message>(MAX_LENGTH);

    /**
     * 获取消息
     * @return
     */
    public static String getMessage(){
        String msg=null;
        if(messageCache.size()>0){
            List<Message> data=new ArrayList<Message>(messageCache.size());
            messageCache.drainTo(data);
            msg= SerializeUtil.serialize(data);
        }
        return msg;
    }

    /**
     * 获取服务器信息
     */
    public static void collectServer(){
        Server server= ServerUtil.getServer();
        Message message=new Message(Server.class.getName(),SerializeUtil.serialize(server));
        messageCache.add(message);
    }

    /**
     * 获取JVM信息
     */
    public static void collectJvm() {
        List<JvmProcess> processes = JvmUtil.getJvmProcesses();
        for (JvmProcess process : processes) {
            Jvm jvm = JvmUtil.getJvm(process);
            Message message = new Message(Jvm.class.getName(), SerializeUtil.serialize(jvm));
            messageCache.add(message);
        }
    }

    /**
     * 获取JVM Start信息
     */
    public static void collectJvmStart() {
        List<JvmProcess> processes = JvmUtil.getJvmProcesses();
        for (JvmProcess process : processes) {
            JvmStart jvmStart = JvmUtil.getJvmStart(process);
            Message message = new Message(JvmStart.class.getName(), SerializeUtil.serialize(jvmStart));
            messageCache.add(message);
        }
    }

    /**
     * 获取JVM Start信息
     */
    public static void collectHeartBeat() {
        MonitorId monitorId = new MonitorId(Agent.getInstance().groupKey, Agent.getInstance().appKey);
        Message message = new Message(MonitorId.class.getName(), SerializeUtil.serialize(monitorId));
        messageCache.add(message);
    }

    /**
     * 添加告警消息
     *
     * @param key 报警点
     * @param msg 报警消息
     */
    public static void alarm(String key, String msg) {
        Alarm alarm = new Alarm(Agent.getInstance().groupKey, Agent.getInstance().appKey, key, msg);
        Message message = new Message(Alarm.class.getName(), SerializeUtil.serialize(alarm));
        messageCache.add(message);
    }

    /**
     * 自定义数据项
     *
     * @param key key
     * @param msg 自定义数据
     */
    public static void metric(String key, HashMap<String, Object> msg) {
        Metric metric = new Metric(Agent.getInstance().groupKey, Agent.getInstance().appKey, key, msg);
        Message message = new Message(Metric.class.getName(), SerializeUtil.serialize(metric));
        messageCache.add(message);
    }
}
