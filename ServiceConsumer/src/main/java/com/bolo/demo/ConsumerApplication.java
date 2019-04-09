package com.bolo.demo;

import com.bolo.ribbon.MyRuleConf;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * 启动类
 */
@EnableEurekaClient
@SpringBootApplication
// 启用ribbon自定义的负载均衡配置，需要添加@RibbonClient注解，name属性指定了使用自定义负载均衡算法的服务名
@RibbonClient(name = "MY-SERVICE-PROVIDER", configuration = MyRuleConf.class)
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class);
    }
}
