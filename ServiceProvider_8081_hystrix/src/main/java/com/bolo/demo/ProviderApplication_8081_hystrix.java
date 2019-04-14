package com.bolo.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动类
 */
// 启动 Eureka Client, 表示将会向配置好的 eureka serice 注册和发现服务
@EnableEurekaClient
@EnableCircuitBreaker // 对hystrixR熔断机制的支持
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("com.bolo.demo.*")
@MapperScan("com.bolo.demo.dao.mapper")
public class ProviderApplication_8081_hystrix {
	public static void main(String[] args) {
		SpringApplication.run(ProviderApplication_8081_hystrix.class);
	}
}
