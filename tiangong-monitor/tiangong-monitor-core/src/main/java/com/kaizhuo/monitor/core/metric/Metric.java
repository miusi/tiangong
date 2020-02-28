package com.kaizhuo.monitor.core.metric;

import com.kaizhuo.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.metric
 * @description: 自定义数据
 * @author: miaochen
 * @create: 2020-02-28 21:40
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Metric extends MonitorId {

    private static final long serialVersionUID = 4059301204096162038L;

    /**
     * 自定义数据点
     */
    private String key;

    /**
      * 自定义数据
     */
    private HashMap<String, Object> msg = new HashMap<>();

    public Metric(String groupKey, String appKey, String key, HashMap<String, Object> msg) {
        super(groupKey, appKey);
        this.key = key;
        this.msg = msg;
    }
}
