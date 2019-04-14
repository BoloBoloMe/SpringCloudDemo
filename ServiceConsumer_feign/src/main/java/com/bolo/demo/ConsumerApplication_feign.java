package com.bolo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * 启动类
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication_feign {
	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication_feign.class);
	}
}
