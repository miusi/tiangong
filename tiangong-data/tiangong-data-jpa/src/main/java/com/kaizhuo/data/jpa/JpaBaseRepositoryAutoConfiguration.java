package com.kaizhuo.data.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;

/**
 * @program: tiangong
 * @package: com.kaizhuo.data.jpa
 * @description:
 * @author: miaochen
 * @create: 2020-02-24 22:14
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@EnableJpaRepositories(repositoryBaseClass = JpaBaseRepositoryFactoryBean.class)
@ComponentScan(basePackages = "com.kaizhuo.data.jpa")
public class JpaBaseRepositoryAutoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(JpaBaseRepositoryAutoConfiguration.class);

    @PostConstruct
    public void postConstruct(){
        logger.info("JpaBaseRepository MODULE LOADED!");
    }
}
