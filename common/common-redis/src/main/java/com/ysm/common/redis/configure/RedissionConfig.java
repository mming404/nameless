package com.ysm.common.redis.configure;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: description:Redission配置类
 * date: 2023年6月25日11:38:49
 */
@Slf4j
@Configuration
public class RedissionConfig {
    private final String REDISSON_PREFIX = "redis://";
    private final RedisProperties redisProperties;
 
    public RedissionConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }
 
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
//        String url = REDISSON_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort();
        // 这里以单台redis服务器为例
//        config.useSingleServer()
//            .setAddress(url)
//            .setPassword(redisProperties.getPassword())
//            .setDatabase(redisProperties.getDatabase())
//            .setPingConnectionInterval(2000);
//        config.setLockWatchdogTimeout(10000L);
 
        // 实际开发过程中应该为cluster或者哨兵模式，这里以cluster为例
        config.useClusterServers()
                .addNodeAddress("redis://10.21.32.86:6371")
                .addNodeAddress("redis://10.21.32.86:6372")
                .addNodeAddress("redis://10.21.32.39:6373")
                .addNodeAddress("redis://10.21.32.39:6374")
                .addNodeAddress("redis://10.21.32.141:6375")
                .addNodeAddress("redis://10.21.32.141:6376")
                .setPassword("hthredis");

        try {
            return Redisson.create(config);
        } catch (Exception e) {
            log.error("RedissonClient init redis url:[{}], Exception:", 1, e);
            return null;
        }
    }
}