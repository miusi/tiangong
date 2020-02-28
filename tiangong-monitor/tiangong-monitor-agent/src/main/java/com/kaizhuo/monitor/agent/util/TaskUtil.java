package com.kaizhuo.monitor.agent.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @program: tiangong
 * @package: com.kaizhuo.monitor.agent.util
 * @description: 定时任务配置
 * @author: miaochen
 * @create: 2020-02-28 22:52
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Component
public class TaskUtil {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public void schedule(Runnable task, int period) {
        long periodLong = period * 1000;
        if (periodLong == 0) {
            periodLong = 60 * 1000L;
        }
        threadPoolTaskScheduler.scheduleAtFixedRate(task, new Date(System.currentTimeMillis() + 50000L), periodLong);
    }

    public void reset() {
        threadPoolTaskScheduler.shutdown();
        threadPoolTaskScheduler.initialize();
    }

    public void resetSchedule(Runnable task, int period) {
        shutdown();
        threadPoolTaskScheduler.initialize();
        schedule(task, period);
    }

    public void shutdown() {
        threadPoolTaskScheduler.shutdown();
    }
}
