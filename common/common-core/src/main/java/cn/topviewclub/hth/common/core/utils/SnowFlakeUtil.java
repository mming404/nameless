package cn.topviewclub.hth.common.core.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;

public class SnowFlakeUtil {
    private static long workerId = 0;
    private static long datacenterId = 1;
    private static Snowflake snowFlake = IdUtil.getSnowflake(workerId,datacenterId);

    static {
        try {
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
        }catch (Exception e) {
            throw new RuntimeException(e);
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
    
}