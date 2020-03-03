package com.kaizhuo.component.rbac.config;

import com.kaizhuo.data.jpa.JpaBaseRepositoryAutoConfiguration;
import com.kaizhuo.data.jpa.JpaBaseRepositoryFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.config
 * @description: 自动配置
 * @author: miaochen
 * @create: 2020-02-28 21:16
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@ComponentScan(basePackages = "com.kaizhuo.component.rbac")
@EntityScan(basePackages = {"com.kaizhuo.component.rbac"})
@EnableJpaRepositories(
        basePackages = {"com.kaizhuo.component.rbac"},
        repositoryFactoryBeanClass = JpaBaseRepositoryFactoryBean.class
)
@Configuration
public class RbacAutoConfiguration  {
    private static final Logger logger = LoggerFactory.getLogger(RbacAutoConfiguration.class);


    @PostConstruct
    public void postConstruct(){
        logger.info("Rbac MODULE LOADED!");
    }

}
