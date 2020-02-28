package com.kaizhuo.monitor.core.alarm;

import com.kaizhuo.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.alarm
 * @description: 自定义报警
 * @author: miaochen
 * @create: 2020-02-28 21:41
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Alarm extends MonitorId {


    private static final long serialVersionUID = -3378091529750696954L;
    /**
     * 自定义报警点
     */
    private String key;

    /**
     * 自定义报警消息
     */
    private String msg;

    public Alarm(String groupKey, String appKey, String key, String msg) {
        super(groupKey, appKey);
        this.key = key;
        this.msg = msg;
    }
}
