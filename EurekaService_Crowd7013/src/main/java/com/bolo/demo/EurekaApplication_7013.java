package com.bolo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Spring Cloud Eureka 启动类
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaApplication_7013 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication_7013.class);
    }
}
