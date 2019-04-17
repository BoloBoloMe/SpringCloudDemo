package com.bolo.demo.service;

import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import com.bolo.demo.enums.ResultCode;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 创建失败回调对象的工厂类
 */
@Component
public class QuerySerFallbackFactory implements FallbackFactory<ClientQueryService> {
    /**
     * @param throwable
     * @return 与业务接口同类型的回调实现
     */
    @Override
    public ClientQueryService create(Throwable throwable) {
        return new ClientQueryService() {
            @Override
            public QueryFruitResp queryFruit(QueryFruitReq req) {
                QueryFruitResp resp = new QueryFruitResp();
                resp.setFail(ResultCode.fail, "服务不可用");
                return resp;
            }
        };
    }
}
