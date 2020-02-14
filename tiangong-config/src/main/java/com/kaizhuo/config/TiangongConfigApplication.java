package com.kaizhuo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TiangongConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiangongConfigApplication.class, args);
    }

}
