package com.kaizhuo.data.jpa;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
public class JpaBaseRepositoryAutoConfiguration {
}
