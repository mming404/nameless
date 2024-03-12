package com.ysm.common.redission.delay;


import com.ysm.common.redission.delay.handle.RedisDelayQueueHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description: 启动延迟队列监测扫描
 * @author:  ysm
 */
@Slf4j
@Component
public class RedisDelayQueueRunner implements CommandLineRunner {

    @Resource
    private RedisDelayQueueUtil redisDelayQueueUtil;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ThreadPoolTaskExecutor ptask;
 
    ThreadPoolExecutor executorService = new ThreadPoolExecutor(
            3,
            5,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000),
            new ThreadPoolExecutor.AbortPolicy());
 
    @Override
    public void run(String... args) throws Exception {
        ptask.execute(() -> {
            while (true){
                try {
                    RedisDelayQueueEnum[] queueEnums = RedisDelayQueueEnum.values();
                    for (RedisDelayQueueEnum queueEnum : queueEnums) {
                        Map<Object,Object> value = redisDelayQueueUtil.getDelayQueue(queueEnum.getCode());
                        if (value != null) {
                            RedisDelayQueueHandle<Map<Object,Object>> redisDelayQueueHandle = (RedisDelayQueueHandle<Map<Object,Object>>)context.getBean(queueEnum.getBeanId());
                            executorService.execute(() -> {redisDelayQueueHandle.execute(value);});
                        }
                    }
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    log.error("(Redission延迟队列监测异常中断) {}", e.getMessage());
                }
            }
        });
        log.info("(Redission延迟队列监测启动成功)");
    }
}