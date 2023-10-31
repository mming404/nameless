package com.ysm.consumer;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import io.micrometer.core.instrument.Meter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.OptionalInt;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/10/11
 * @Version V1.0
 **/
@Slf4j
public class SnowFlakeDemo {
    private static long workerId = 0;
    private static long datacenterId = 1;
    private static Snowflake snowFlake = IdUtil.getSnowflake(workerId,datacenterId);

    static {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e){
            log.error(e.getMessage());
        }




    }

    /**
     * 获取雪花ID
     * @return id
     */
    public static synchronized long snowflakeId() {
        return snowFlake.nextId();
    }

    public static synchronized long snowflakeId(long workerId) {
        Snowflake snowflake = IdUtil.getSnowflake(workerId);
        return snowflake.nextId();
    }

    public static synchronized long snowflakeId(long workerId, long datacenterId) {
        Snowflake snowflake = IdUtil.getSnowflake(workerId, datacenterId);
        return snowflake.nextId();
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,5,6};
    }

}
