package com.kaizhuo.monitor.agent.handler;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.handler
 * @description: 利用kafka发送数据
 * @author: miaochen
 * @create: 2020-02-28 22:56
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
public class KafkaSender implements Sender {
    private static volatile KafkaSender sender;

    public static KafkaSender getInstance() {
        if (sender == null) {
            synchronized (KafkaSender.class) {
                if (sender == null) {
                    sender = new KafkaSender();
                }
            }
        }
        return sender;
    }

    @Override
    public boolean sendData(String msg) {
        return false;
    }
}
