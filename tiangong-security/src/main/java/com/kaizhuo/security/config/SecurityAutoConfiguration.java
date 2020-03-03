package com.kaizhuo.security.config;

import com.kaizhuo.security.auth.common.TiangongAccessDeniedHandler;
import com.kaizhuo.security.auth.common.TiangongAuthenticationEntryPoint;
import com.kaizhuo.security.auth.common.TiangongAuthenticationFailureHandler;
import com.kaizhuo.security.auth.common.TiangongLogoutSuccessHandler;
import com.kaizhuo.security.auth.jwt.*;
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
    public TiangongAuthenticationEntryPoint tiangongAuthenticationEntryPoint() {
        return new TiangongAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
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
    public JwtAuthenticationProvider jwtAuthenticationProvier() {
        return new JwtAuthenticationProvider();
    }

    @Bean
    @ConditionalOnMissingBean
    public SecurityProperties securityProperties() {
        return new SecurityProperties();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtUserDetailsService jwtUserDetailsService() {
        return new JwtUserDetailsService();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Bean
    @ConditionalOnMissingBean
    public JwtAuthenticationRefreshFilter jwtAuthenticationRefreshFilter() {
        return new JwtAuthenticationRefreshFilter();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
