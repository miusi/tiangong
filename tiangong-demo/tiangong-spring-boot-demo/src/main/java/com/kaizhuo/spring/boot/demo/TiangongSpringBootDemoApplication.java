package com.kaizhuo.spring.boot.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
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
