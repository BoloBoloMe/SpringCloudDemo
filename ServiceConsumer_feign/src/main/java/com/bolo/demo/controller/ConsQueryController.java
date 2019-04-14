package com.bolo.demo.controller;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import com.bolo.demo.service.ClientQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 消费者的查询业务
 */
@RestController
public class ConsQueryController {
	
	/**
	 * 基于feign的查续服务接口
	 */
	@Autowired
	private ClientQueryService queryServce;

	/**
	 * 查询水果
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "fruit", method = RequestMethod.GET)
	public QueryFruitResp queryFruit(QueryFruitReq req) {
		return queryServce.queryFruit(req);
	}
}
