package com.kaizhuo.monitor.core.method;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.method
 * @description: 方法调用
 * @author: miaochen
 * @create: 2020-02-28 21:46
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class MethodCall {
    private String className;
    private String methodName;
    private String spanId;
    private String parentId;
    private String traceId;
    private long beginTime;
    private long endTime;
    private long callTime;
    private int errCode;
    private String errorMsg;
}
