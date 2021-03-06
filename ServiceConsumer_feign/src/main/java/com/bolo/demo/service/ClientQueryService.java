package com.bolo.demo.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;

/**
 * 基于 feign组件 定义的查续服务接口。定义规则：定义接口，使用 @FeignClient 注解
 *
 * @author 罗敬研
 */
// 1.单纯地使用FeignClient
//@FeignClient(value = "MY-SERVICE-PROVIDER")

// 2.设置fallbackFactory属性—— 用于创建ClientQueryService方法调用失败后的回调实例的工厂类，看来feign 集成了hystrix
@FeignClient(value = "MY-SERVICE-PROVIDER", fallbackFactory = QuerySerFallbackFactory.class)
public interface ClientQueryService {
    /**
     * 查询水果信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "/fruit", method = RequestMethod.POST)
    QueryFruitResp queryFruit(@RequestBody QueryFruitReq req);
}
