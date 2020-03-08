package com.kaizhuo.component.rbac.mybatis.config;

import com.kaizhuo.component.rbac.mybatis.config.mybatis.MybatisPlusConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.rbac.mybatis.config.mybatis
 * @description:
 * @author: godric
 * @create: 2020/3/8 0008
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: godric
 **/
@Configuration
@Import(
        {MybatisPlusConfig.class})
public class MyBatisRbacAutoConfig {
}
