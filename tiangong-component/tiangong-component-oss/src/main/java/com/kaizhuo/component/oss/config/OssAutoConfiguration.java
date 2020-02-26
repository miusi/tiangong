package com.kaizhuo.component.oss.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.oss.config
 * @description: 自动配置
 * @author: miaochen
 * @create: 2020-02-25 21:40
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@Import(
        {
                OssProperties.class
        }
)
public class OssAutoConfiguration {
}
