package com.kaizhuo.cloud.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 服务注册中心
 */
@SpringBootApplication
@EnableEurekaServer
public class TiangongRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiangongRegisterApplication.class, args);
    }

}
