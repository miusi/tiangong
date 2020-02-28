package com.kaizhuo.monitor.agent.util;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.util
 * @description: 方法拦截器
 * @author: miaochen
 * @create: 2020-02-28 22:51
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
//@Aspect
//@Order(1)
public class MethodIntercept {

    //    @Around("@annotation(io.github.weechang.moreco.monitor.sdk.io.github.weechang.moreco.monitor.agent.annation.MethodMonitor)")
//    public Object around(ProceedingJoinPoint pjp) throws Throwable {
//        MethodMonitor method = (MethodMonitor)this.getLogAnnotation(pjp, MethodMonitor.class);
//        if (method != null) {
//            MethodInfo mi = Monitor.methodStart(method.key());
//
//            Object var5;
//            try {
//                Object result = pjp.proceed();
//                var5 = result;
//            } catch (Throwable var9) {
//                if (method.success()) {
//                    Monitor.methodFail(mi);
//                }
//
//                throw var9;
//            } finally {
//                Monitor.methodFinish(mi);
//            }
//
//            return var5;
//        } else {
//            return pjp.proceed();
//        }
//    }
//
//    private <T extends Annotation> T getLogAnnotation(JoinPoint jp, Class<T> type) {
//        SignatureAttribute.MethodSignature methodSignature = (SignatureAttribute.MethodSignature)jp.getSignature();
//        Method targetMethod = methodSignature.getMethod();
//        return targetMethod.getAnnotation(type);
//    }
}
