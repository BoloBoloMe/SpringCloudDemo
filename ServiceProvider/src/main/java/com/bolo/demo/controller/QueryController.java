package com.bolo.demo.controller;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import com.bolo.demo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询业务
 */
@RestController
public class QueryController {
    @Autowired
    private QueryService queryService;

    /**
     * 查询水果
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "fruit", method = RequestMethod.GET)
    public QueryFruitResp queryFruit(QueryFruitReq req) {
        return queryService.queryFruit(req);
    }
}
