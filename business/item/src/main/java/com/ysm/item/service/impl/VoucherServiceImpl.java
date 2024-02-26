package com.ysm.item.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.common.core.utils.SnowFlakeUtil;
import com.ysm.common.kafka.constant.KafkaConstant;
import com.ysm.item.mapper.VoucherMapper;
import com.ysm.item.po.Voucher;
import com.ysm.item.service.VoucherService;
import com.ysm.user.api.UserServiceI;
import com.ysm.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

/**
* @author 86139
* @description 针对表【voucher】的数据库操作Service实现
* @createDate 2023-11-03 22:03:23
*/
@Service
public class VoucherServiceImpl extends ServiceImpl<VoucherMapper, Voucher>
    implements VoucherService {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @DubboReference
    private UserServiceI userServiceI;


    /**
     * 初始化lua脚本
     */
    private static final DefaultRedisScript<Long> KILL_SCRIPT;
    static {
        KILL_SCRIPT = new DefaultRedisScript<>();
        KILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        KILL_SCRIPT.setResultType(Long.class);
    }

    @Override
    public CommonResult<String> secKill(Long userId, Long voucherId) {

        // TODO: 2023/11/8 根据userid rpc调用用户服务
        UserDTO userDTO = userServiceI.getUserDTOById(userId);
        // TODO: 2023/11/8 基于lua脚本判断库存和 一人一单
        Long res = stringRedisTemplate.execute(KILL_SCRIPT, Collections.emptyList(), voucherId.toString(), userId.toString());
        if (res != null && res.intValue() != 0) {
            return CommonResult.fail(res == 1 ? "库存不足" : "不能重复下单");
        }
        // TODO: 2023/11/8 如果符合条件抢购成功 封装id发送消息队列
        long orderId = SnowFlakeUtil.snowflakeId();
        HashMap<String, Long> msgHashMap = new HashMap<>();
        msgHashMap.put("voucherId",voucherId);
        msgHashMap.put("userId",userId);
        kafkaTemplate.send(KafkaConstant.VOUCHER_KILL_ORDER_TOPIC, String.valueOf(orderId), JSON.toJSONString(msgHashMap));

        //事务消息  实际生产环境需要把上面的代码放入下面的重写方法中 同时需要开启幂等消息，ISR列表为all
//        kafkaTemplate.executeInTransaction(kafkaOperations -> {
//            kafkaOperations.send(KafkaConstant.VOUCHER_KILL_ORDER_TOPIC, String.valueOf(orderId), JSON.toJSONString(msgHashMap));
//            return "ok";
//        });
        return CommonResult.ok();
    }
}




