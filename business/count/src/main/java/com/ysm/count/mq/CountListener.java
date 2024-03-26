package com.ysm.count.mq;

import com.ysm.common.kafka.constant.KafkaConstant;
import com.ysm.count.batch.BatchUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/23
 * @Version V1.0
 **/
@Component
public class CountListener {

    @Autowired
    private RedisTemplate redisTemplate;

    private BatchUtil<Integer, Integer> commentBatchUtil;

    private BatchUtil<Integer, Integer> collectBatchUtil;

    private Long commentItemId;

    private Long collectItemId;

    @PostConstruct
    private void postConstructorInit() {
        // 批量处理comment
        commentBatchUtil = BatchUtil.getInstance((input, handlerType) -> {
            System.out.println("处理类型:" + handlerType + ",接受到批量comment请求参数:" + input);
            int sum = input.stream().mapToInt(x -> x).sum();
            if (sum != 0){
                redisTemplate.opsForValue().increment("item:" + commentItemId + ":comment", sum);
            }
            return null;
        }, 100, 5);

        // 批量处理collect
        collectBatchUtil = BatchUtil.getInstance((input, handlerType) -> {
            System.out.println("处理类型:" + handlerType + ",接受到批量collect请求参数:" + input);
            int sum = input.stream().mapToInt(x -> x).sum();
            if (sum != 0){
                redisTemplate.opsForValue().increment("item:" + collectItemId + ":collect", sum);
            }
            return null;
        }, 100, 5);
    }

    @KafkaListener(topics = "item_count_comment",groupId = KafkaConstant.DEFAULT_GROUP)
    public void listenComment(ConsumerRecord<String, String> record, Acknowledgment ack){
        String[] split = record.value().split(":");
        commentItemId = Long.valueOf(split[0]);
        commentBatchUtil.addRequestParam(Integer.valueOf(split[1]));
        ack.acknowledge();
    }

    @KafkaListener(topics = "item_count_collect",groupId = KafkaConstant.DEFAULT_GROUP)
    public void listenCollect(ConsumerRecord<String, String> record, Acknowledgment ack){
        String[] split = record.value().split(":");
        collectItemId = Long.valueOf(split[0]);
        collectBatchUtil.addRequestParam(Integer.valueOf(split[1]));
        ack.acknowledge();
    }
}
