package com.kaizhuo.component.swagger;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.swagger
 * @description: swagger注解
 * @author: miaochen
 * @create: 2020-02-25 08:04
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@ConditionalOnProperty(name = SwaggerConfiguration.enableKey, matchIfMissing = true)
@Import({Swagger2DocumentationConfiguration.class})
public class SwaggerConfiguration {
    /**
     * 前缀key
     */
    public static final String preKey = "tiangong.swagger";

    /**
     * 可用key
     */
    public static final String enableKey = "tiangong.swagger.enabled";
}
