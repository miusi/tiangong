package com.kaizhuo.component.swagger;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: tiangong
 * @package: com.kaizhuo.component.swagger
 * @description: swagger自动注入
 * @author: miaochen
 * @create: 2020-02-25 08:13
 * @copyright: CopyRight (c) 2020
 * @version: 1.0.0
 * @modified: miaochen
 **/
@Configuration
@ConditionalOnProperty(name=SwaggerConfiguration.enableKey,matchIfMissing = true)
@Import(
        SwaggerConfiguration.class
)
public class SwaggerAutoConfiguration implements BeanFactoryAware {

    private BeanFactory beanFactory;
    

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }
}
