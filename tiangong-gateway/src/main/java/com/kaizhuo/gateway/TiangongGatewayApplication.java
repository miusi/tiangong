package com.kaizhuo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class TiangongGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TiangongGatewayApplication.class, args);
    }

}
