package com.kaizhuo.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TiangongRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiangongRegisterApplication.class, args);
    }

}
