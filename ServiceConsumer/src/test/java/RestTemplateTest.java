import com.bolo.demo.ConsumerApplication;
import com.bolo.demo.controller.ServiceUrl;
import com.bolo.demo.entity.req.QueryFruitReq;
import com.bolo.demo.entity.resp.QueryFruitResp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest 模板类测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class)
public class RestTemplateTest {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 测试发送get请求
     */
    @Test
    public void testSendGetReq() {
        QueryFruitResp queryFruitResp = restTemplate.getForObject(ServiceUrl.GET_FRUITS + "?type=热带水果1", QueryFruitResp.class);
        System.out.println(queryFruitResp);
    }
}
