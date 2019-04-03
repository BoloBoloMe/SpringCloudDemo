package com.bolo.demo.service.impl;

import com.bolo.demo.dao.entitys.Fruit;
import com.bolo.demo.dao.mapper.FruitMapper;
import com.bolo.demo.enums.ResultCode;
import com.bolo.demo.service.QueryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bolo.demo.entity.req.*;
import com.bolo.demo.entity.resp.*;
import com.bolo.demo.entity.pojo.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 查询业务逻辑层实现
 */
@Service
public class QueryServiceImpl implements QueryService {
    private Logger logger = LoggerFactory.getLogger(QueryServiceImpl.class);

    @Autowired
    private FruitMapper fruitMapper;

    /**
     * 查询水果
     *
     * @param req
     * @return
     */
    @Override
    public QueryFruitResp queryFruit(QueryFruitReq req) {
        logger.info("excuse queryFruit methon. req is {}",req.toString());
        QueryFruitResp resp = new QueryFruitResp();
        try {
            Fruit example = new Fruit();
            example.setType(req.getType());
            example.setSciName(req.getSciName());
            List<Fruit> resultSet = fruitMapper.getByExample(example);
            if (resultSet != null) {
                resp.setSize(resultSet.size());
                List<FruitPojo> pojos = resultSet.stream().map(fruit -> {
                    FruitPojo pojo = new FruitPojo();
                    BeanUtils.copyProperties(fruit, pojo);
                    return pojo;
                }).collect(Collectors.toList());
                resp.setPojos(pojos);
            } else {
                resp.setSize(0);
            }
            resp.setSucceed();
        } catch (Exception e) {
            logger.error("查询水果信息失败", e);
            resp.setFail(ResultCode.fail, e.getMessage());
        }
        return resp;
    }
}
