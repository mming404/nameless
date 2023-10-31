package com.ysm.provider;

import com.alibaba.fastjson2.JSON;
import com.ysm.entity.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/27
 * @Version V1.0
 **/
@Component
public class MyProvider {
    private static final String TOPIC_NAME = "my_topic";
    private static final String IPHONE_TOPIC = "my_topic1";
    private static final String IPAD_TOPIC = "my_topic2";

    @KafkaListener(topics = "my-replicated-topic",groupId = "default-group")
    public void listenGroup(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println(value);
        //手动提交offset
        ack.acknowledge();
    }

    /**
     * 指定多个主题。
     *
     * @param record
     */
    @KafkaListener(topics = {IPHONE_TOPIC,IPAD_TOPIC},groupId = "default-group")
    public void topics(ConsumerRecord<String, String> record, Acknowledgment ack) {
        System.out.println("进入topics方法");
        System.out.printf(
                "主题 = %s,分区 = %d, 偏移量 = %d, key = %s, 内容 = %s,创建消息的时间戳 =%d%n",
                record.topic(),
                record.partition(),
                record.offset(),
                record.key(),
                record.value(),
                record.timestamp()
        );
        ack.acknowledge();
    }


//    @KafkaListener(topics = "my-replicated-topic",groupId = "default-group2",topicPattern = "1")
//    public void listenGroup2(ConsumerRecord<String, String> record, Acknowledgment ack) {
//        String value = record.value();
//        System.out.println(value);
//        //手动提交offset
//        ack.acknowledge();
//    }
//    @KafkaListener(topics = "topic1")
//    public void listen(String in){
//        System.out.println(in);
//    }


}
