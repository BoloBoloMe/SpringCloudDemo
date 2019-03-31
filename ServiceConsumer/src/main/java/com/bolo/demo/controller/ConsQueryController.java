package com.bolo.demo.controller;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
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
    @Autowired
    RestTemplate restTemplate;

    /**
     * 查询水果
     *
     * @param req
     * @return
     */
    @RequestMapping(value = "fruit", method = RequestMethod.GET)
    public QueryFruitResp queryFruit(QueryFruitReq req) {
        StringBuilder url = new StringBuilder(ServiceUrl.GET_FRUITS);
        if (!StringUtils.isEmpty(req.getType()) || !StringUtils.isEmpty(req.getSciName())) {
            url.append('?');
            if (req.getType() != null) {
                url.append("type=").append(req.getType());
            }
            if (req.getSciName() != null) {
                url.append("sciName=").append(req.getSciName());
            }
        }
        return restTemplate.getForObject(url.toString(), QueryFruitResp.class, req);
    }
}
