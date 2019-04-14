package com.bolo.demo.service;

import com.bolo.demo.entity.req.*;
import com.bolo.demo.entity.resp.*;

/**
 * 查询业务逻辑层接口
 */
public interface QueryService {
	/**
	 * 查询水果信息
	 * 
	 * @param req
	 * @return
	 */
	QueryFruitResp queryFruit(QueryFruitReq req);
}
