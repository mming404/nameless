package com.ysm.order.mq;

import com.alibaba.fastjson2.JSON;
import com.ysm.common.kafka.constant.KafkaConstant;
import com.ysm.order.service.OrderService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/8
 * @Version V1.0
 **/
@Component
public class KafkaOrderListener {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors()*2,
            Runtime.getRuntime().availableProcessors()*4,
            5000, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(100),
            new ThreadPoolExecutor.CallerRunsPolicy()
            );

    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = KafkaConstant.VOUCHER_KILL_ORDER_TOPIC,groupId = KafkaConstant.DEFAULT_GROUP)
    public void listenSecKillVoucher(ConsumerRecord<String, String> record, Acknowledgment ack){
        String value = record.value();
        HashMap hashMap = JSON.parseObject(value, HashMap.class);
        System.out.println(hashMap.get("voucherId"));
        // TODO: 2023/11/8 在order服务中监听队列，使用线程池异步消费
        CompletableFuture.runAsync(
                () -> doOrder(hashMap)
        ,executor).whenCompleteAsync((unused, throwable) -> {
            System.out.println(throwable);
        });
        //手动提交offset
        ack.acknowledge();
    }


    private void doOrder(HashMap<String,Long> map){
        System.out.println("生成订单");
//        orderService.createOrder()
    }


}
