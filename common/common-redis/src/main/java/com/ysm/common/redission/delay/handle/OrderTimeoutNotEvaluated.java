package com.ysm.common.redission.delay.handle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 订单超时未评价处理类
 */
@Component(value = "orderTimeoutNotEvaluated")
@Slf4j
public class OrderTimeoutNotEvaluated implements RedisDelayQueueHandle<Map<Object,Object>> {
    @Override
    public void execute(Map<Object,Object> map) {
        log.info("(收到订单超时未评价延迟消息) {}", map);
        // TODO 订单超时未评价，系统默认好评处理业务...
        String id =(String) map.get("orderId");
        String msg =(String) map.get("remark");
        System.out.println("id = " + id);
        System.out.println("msg = " + msg);
    }
}