package com.ysm.common.redission.delay.handle;

/**
 * 延迟队列执行器
 * @author ysm
 */
public interface RedisDelayQueueHandle<T> {

    /**
     * 延迟队列执行器
     * @param t 参数
     */
    void execute(T t);
 
}