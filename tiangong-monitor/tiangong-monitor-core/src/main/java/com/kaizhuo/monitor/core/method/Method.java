package com.kaizhuo.monitor.core.method;

import com.kaizhuo.monitor.core.common.MonitorId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.method
 * @description: 方法
 * @author: miaochen
 * @create: 2020-02-28 21:47
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
public class Method  extends MonitorId {

    private static final long serialVersionUID = 1381166533213760701L;

    private final long beginTime=System.currentTimeMillis();
    private final long bt=System.nanoTime();
    private int count=1;
    private long extime=0L;
    private long et=0L;
    private int success=1;
    private String key;

    public Method(String projectCode, String appCode, String methodKey) {
        super(projectCode, appCode);
        this.key = methodKey;
    }

    public void completed() {
        this.extime = System.currentTimeMillis() - this.beginTime;
        this.et = System.nanoTime() - this.bt;
    }

}
