package com.ysm.common.redission.delay;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 延迟队列业务枚举
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum RedisDelayQueueEnum {

    /**
     * 订单支付超时，自动取消订单
     */
    ORDER_PAYMENT_TIMEOUT("ORDER_PAYMENT_TIMEOUT","订单支付超时，自动取消订单", "orderPaymentTimeout"),

    /**
     * 订单超时未评价，系统默认好评
     */
    ORDER_TIMEOUT_NOT_EVALUATED("ORDER_TIMEOUT_NOT_EVALUATED", "订单超时未评价，系统默认好评", "orderTimeoutNotEvaluated");
 
    /**
     * 延迟队列 Redis Key
     */
    private String code;
 
    /**
     * 任务描述
     */
    private String name;
 
    /**
     * 延迟队列具体业务实现的 Bean
     * 可通过 Spring 的上下文获取
     */
    private String beanId;
 
}