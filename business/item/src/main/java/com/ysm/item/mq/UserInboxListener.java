package com.ysm.item.mq;

import com.alibaba.fastjson2.JSON;
import com.ysm.common.kafka.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.util.HashMap;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/2
 * @Version V1.0
 **/
@Component
public class UserInboxListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @KafkaListener(topics = "user_inbox",groupId = KafkaConstant.DEFAULT_GROUP)
    public void listenInbox(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        HashMap hashMap = JSON.parseObject(value, HashMap.class);
        redisTemplate.opsForZSet().add("feed:inbox:"+hashMap.get("fanId"),
                hashMap.get("itemId"),
                Double.parseDouble(hashMap.get("score").toString()));
        ack.acknowledge();
    }
}
