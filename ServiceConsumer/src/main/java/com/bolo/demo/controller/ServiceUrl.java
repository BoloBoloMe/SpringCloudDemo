package com.bolo.demo.controller;

/**
 * 服务地址枚举
 */
public class ServiceUrl {
    /**
     * 服务地址前缀
     */
//    final private static String prefix = "http://localhost:8080"; // 直接通过http请求调用服务端的Contrller
    final private static String prefix = "http://MY-SERVICE-PROVIDER"; // 通过eureka以微服务的方式进行调用：将ip地址端口号改为微服务的服务名

    // 获取水果信息
    final public static String GET_FRUITS = prefix + "/fruit";
}
