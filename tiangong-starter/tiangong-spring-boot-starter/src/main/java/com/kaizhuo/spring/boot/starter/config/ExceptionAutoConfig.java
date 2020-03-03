package com.kaizhuo.spring.boot.starter.config;

import com.kaizhuo.spring.boot.starter.exception.CommonExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @program: tiangong
 * @package: com.kaizhuo.spring.boot.starter.config
 * @description: spring mvc 异常映射
 * @author: miaochen
 * @create: 2020-02-29 14:50
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@Slf4j
public class ExceptionAutoConfig {

    @PostConstruct
    public void postConstruct() {
        log.info("Exception MODULE LOAD!");
    }

    @Bean
    @ConditionalOnMissingBean({CommonExceptionHandler.class})
    public CommonExceptionHandler defaultExceptionAdvice() {
        return new CommonExceptionHandler();
    }
}
