package com.kaizhuo.monitor.core.method;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.core.method
 * @description: 方法
 * @author: miaochen
 * @create: 2020-02-28 21:44
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@NoArgsConstructor
@Data
public class MethodInvocationData {
    private String methodName;
    private Long beginDate;
    private List<Long> usedTimes=new ArrayList<>();
    private  MethodInvocationData parent;
    private List<MethodInvocationData> chlidList=new ArrayList<>();
}
