package com.kaizhuo.monitor.agent.annation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.annation
 * @description: 方法监控注解
 * @author: miaochen
 * @create: 2020-02-28 22:14
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Method {
    String key();
    boolean success() default true;
    long totalTime() default 0L;
    long usedTime() default 0L;
}
