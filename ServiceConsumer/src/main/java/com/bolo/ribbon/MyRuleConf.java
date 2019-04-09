package com.bolo.ribbon;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义ribbon负载均衡规则配置类，
 * 当前类一般放在@ComponentScan注解指定的包及其子包路径之外，否则自定义的负载均衡规则会全局有效；
 * 由于@SpringBootApplication注解中含有@ComponentScan注解，所以不能放在启动类所在包及其子包下；
 */
@Configuration
public class MyRuleConf {

    /**
     * 返回自定义负载均衡规则的bean
     * bean id 必须与com.bolo.demo.config.ConfigBean#iRule()的bean id 一致，也就是方法名都要是 iRule()
     * 否则自动注入IRule bean 时会找到两个bean
     *
     * @return
     */
    @Bean
    public IRule iRule() {
        //return new RandomRule();// 随机
        //return new RoundRobinRule();// 轮询
        return new MyRoundRobinRule();// 自定义负载均衡算法：轮询，每台机器连续执行5次
    }
}
