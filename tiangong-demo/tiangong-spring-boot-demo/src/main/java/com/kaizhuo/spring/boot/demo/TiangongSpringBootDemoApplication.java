package com.kaizhuo.spring.boot.demo;

import com.kaizhuo.data.jpa.JpaBaseRepositoryFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Slf4j
@EnableJpaAuditing
@ComponentScan(basePackages = "com.kaizhuo")
public class TiangongSpringBootDemoApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {

        try {
            SpringApplication.run(TiangongSpringBootDemoApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("the exception is {}", e);
        }
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TiangongSpringBootDemoApplication.class);
    }
}
