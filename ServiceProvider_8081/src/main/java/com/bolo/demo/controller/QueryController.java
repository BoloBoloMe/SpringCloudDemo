package com.bolo.demo.controller;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import com.bolo.demo.service.QueryService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 查询业务
 */
@RestController
public class QueryController {
    @Autowired
    private QueryService queryService;

    /**
     * spring cloud 提供服务发现功能的接口
     */
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    /**
     * 查询水果
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "fruit", method = RequestMethod.GET)
    public QueryFruitResp queryFruit(QueryFruitReq req, HttpServletRequest request) {
        QueryFruitResp resp = queryService.queryFruit(req);
        resp.setInstanceId(instanceId);
        return resp;
    }

    /**
     * 服务发现接口：返回给调用者所有注册的微服务
     *
     * @return
     */
    @RequestMapping(value = "service", method = RequestMethod.GET)
    public Object queryService() {
        // 用map作为响应类
        List<TreeMap<String, Object>> resp = new ArrayList<>();
        // 查询服务名
        List<String> service = discoveryClient.getServices();
        service.forEach(s -> {
            // 获取当前服务的所有实例
            List<ServiceInstance> instances = discoveryClient.getInstances(s);

            TreeMap<String, Object> serviceInfo = new TreeMap<>();
            serviceInfo.put("service", s);
            serviceInfo.put("count_instance", instances.size());
            serviceInfo.put("instance_info", instances.stream().map(
                    // 拼接实例信息字符串
                    instance -> new StringBuilder(instance.getHost()).append(':').append(instance.getPort()).append('/').append(instance.getServiceId()).toString())
                    .collect(Collectors.toList()));

            resp.add(serviceInfo);
        });
        return resp;
    }
}
