package com.ysm.feed.mq;

import com.ysm.common.kafka.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class UserInboxListener {

    @Autowired
    private RedisTemplate redisTemplate;

    @KafkaListener(topics = "user_inbox_del",groupId = KafkaConstant.DEFAULT_GROUP)
    public void listenInbox(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        String[] split = value.split(":");
        long userId = Long.parseLong(split[0]);
        long offset = Long.parseLong(split[1]);
        redisTemplate.opsForZSet().removeRangeByScore("feed:inbox:"+userId,0,offset);
        ack.acknowledge();
    }
}