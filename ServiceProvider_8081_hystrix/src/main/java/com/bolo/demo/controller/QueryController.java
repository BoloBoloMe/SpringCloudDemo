package com.bolo.demo.controller;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import com.bolo.demo.enums.ResultCode;
import com.bolo.demo.service.QueryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestBody;
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
	 * 
	 * @RequestBody：通过restTemplate 发送POST 请求并且发送了请求类时，必须以json的数据格式接收请求对象
	 */
	@RequestMapping(value = "fruit", method = RequestMethod.POST)
	// 服务熔断：一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "queryFruitFallBack")
	public QueryFruitResp queryFruit(@RequestBody QueryFruitReq req, HttpServletRequest request) {
		// 人为制造一个异常抛出: 如果传入参数中带有"exce"字符串就抛出异常
		if ("exce".equalsIgnoreCase(req.getSciName()) || "exce".equalsIgnoreCase(req.getType())) {
			throw new RuntimeException("抛出指定异常");
		}

		QueryFruitResp resp = queryService.queryFruit(req);
		resp.setInstanceId(instanceId);
		return resp;
	}

	/**
	 * 查询水果方法的错误回调方法
	 * 
	 * @param req
	 * @param request
	 * @return
	 */
	public QueryFruitResp queryFruitFallBack(@RequestBody QueryFruitReq req, HttpServletRequest request) {
		QueryFruitResp fallBack = new QueryFruitResp();
		fallBack.setFail(ResultCode.fail, "查询失败，系统出现异常！！！");
		return fallBack;
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
					instance -> new StringBuilder(instance.getHost()).append(':').append(instance.getPort()).append('/')
							.append(instance.getServiceId()).toString())
					.collect(Collectors.toList()));

			resp.add(serviceInfo);
		});
		return resp;
	}
}
