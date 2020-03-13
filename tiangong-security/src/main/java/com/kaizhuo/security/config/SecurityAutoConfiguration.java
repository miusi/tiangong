package com.kaizhuo.security.config;

import com.kaizhuo.security.service.authentication.TiangongAuthenticationEntryPoint;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationRefreshFilter;
import com.kaizhuo.security.service.authentication.filter.TiangongAuthenticationTokenFilter;
import com.kaizhuo.security.service.authentication.handler.TiangongAccessDeniedHandler;
import com.kaizhuo.security.service.authentication.handler.TiangongAuthenticationFailureHandler;
import com.kaizhuo.security.service.authentication.handler.TiangongAuthenticationSuccessHandler;
import com.kaizhuo.security.service.authentication.handler.TiangongLogoutSuccessHandler;
import com.kaizhuo.security.service.authentication.provider.TiangongAuthenticationProvider;
import com.kaizhuo.security.service.impl.TiangongUserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * @program: tiangong
 * @package: com.kaizhuo.security.config
 * @description: 自动配置
 * @author: miaochen
 * @create: 2020-02-29 14:06
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ComponentScan(value = {"com.kaizhuo.security"})
@Configuration
@Import(
        SecurityProperties.class
)
public class SecurityAutoConfiguration implements BeanFactoryAware {
    private static final Logger logger = LoggerFactory.getLogger(SecurityAutoConfiguration.class);

    @PostConstruct
    public void postConstruct(){
        logger.info("Security MODULE Loaded");
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationEntryPoint tiangongAuthenticationEntryPoint(){
        return new TiangongAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationSuccessHandler tiangongAuthenticationSuccessHandler() {
        return new TiangongAuthenticationSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationFailureHandler tiangongAuthenticationFailureHandler() {
        return new TiangongAuthenticationFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongLogoutSuccessHandler tiangongLogoutSuccessHandler() {
        return new TiangongLogoutSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAccessDeniedHandler tiangongAccessDeniedHandler() {
        return new TiangongAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationProvider tiangongAuthenticationProvider() {
        return new TiangongAuthenticationProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongUserDetailsServiceImpl tiangongUserDetailsService() {
        return new TiangongUserDetailsServiceImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationTokenFilter tiangongAuthenticationTokenFilter() {
        return new TiangongAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public TiangongAuthenticationRefreshFilter tiangongAuthenticationRefreshFilter() {
        return new TiangongAuthenticationRefreshFilter();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
