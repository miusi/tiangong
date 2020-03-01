package com.kaizhuo.spring.boot.starter.config;

import com.kaizhuo.spring.boot.starter.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @program: tiangong
 * @package: com.kaizhuo.spring.boot.starter.config
 * @description:
 * @author: miaochen
 * @create: 2020-02-29 14:52
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
public class CorsInterceptorConfig  extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor());
        super.addInterceptors(registry);
    }
}
