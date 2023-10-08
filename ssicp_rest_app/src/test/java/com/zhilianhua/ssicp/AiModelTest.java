package com.zhilianhua.ssicp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class AiModelTest {

    @Test
    public void test() {
        String params = "{\"age\":12,\"name\":\"张三\",\"pipe\":{\"id\":\"1213123\"},\"list\":[123,345,667],\"sex\":false}";
        String response = new RestTemplate().postForObject("http://localhost:8081", params, String.class);
        log.debug("response:{}", response);
    }
}
