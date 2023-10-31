package com.ysm.consumer;

import com.alibaba.fastjson2.JSON;
import com.ysm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@RestController
public class TestController {

    @Autowired
    private UserServiceTest userServiceTest;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final static String TOPIC_NAME = "my-replicated-topic";

    private final static String TOPIC_NAME2 = "my-replicated-topic2";

    private static final String IPHONE_TOPIC = "my_topic1";
    private static final String IPAD_TOPIC = "my_topic2";

    @GetMapping("/test")
    public String test() {
        System.out.println("方法执行中。。。");
        userServiceTest.test1();
        kafkaTemplate.send(TOPIC_NAME, 0, "key1", "分区 0：");
        kafkaTemplate.send(IPHONE_TOPIC, "my_topic1");
        kafkaTemplate.send(IPAD_TOPIC, "my_topic2");

//        for (int i = 0; i < 10; i++) {
//            kafkaTemplate.send(TOPIC_NAME, 1, "key2", "分区 1：" + i);
//        }

        return "ok";
    }
}
