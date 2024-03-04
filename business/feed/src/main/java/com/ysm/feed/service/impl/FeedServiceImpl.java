package com.ysm.feed.service.impl;

import com.ysm.feed.entity.vo.FeedVo;
import com.ysm.feed.service.FeedService;
import com.ysm.item.api.ItemServiceIRPC;
import com.ysm.user.api.UserServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private UserServiceI userService;

    @Autowired
    private ItemServiceIRPC itemService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public List<FeedVo> feed(Long userId, Integer size) {
        return null;
    }
}
