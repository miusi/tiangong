package com.kaizhuo.spring.boot.demo.config;/**
 * @program: tiangong
 * @package: com.kaizhuo.spring.boot.demo.config
 * @description:
 * @author: Administrator
 * @create: 2020/3/3 0003
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: Administrator
 **/

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


/**
 \* Created with IntelliJ IDEA. 
 \* User: Administrator 
 \* Date: 2020/3/3 0003 
 \* Time: 22:27 
 \* To change this template use File | Settings | File Templates. 
 \* Description: 
 \*/
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     * 需要重新指定静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }




    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
