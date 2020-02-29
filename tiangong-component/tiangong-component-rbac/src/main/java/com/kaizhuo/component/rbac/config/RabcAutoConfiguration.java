package com.kaizhuo.component.rbac.config;

import com.kaizhuo.data.jpa.JpaBaseRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
@ComponentScan(basePackages = "com.kaizhuo.component.rbac.dao")
@EntityScan({"com.kaizhuo.component.rbac"})
@EnableJpaRepositories(
        basePackages = {"com.kaizhuo.component.rbac"},
        repositoryFactoryBeanClass = JpaBaseRepositoryFactoryBean.class
)
@Configuration
public class RabcAutoConfiguration {
}
