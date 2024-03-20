package com.ysm.feed.service.impl;

import com.ysm.feed.entity.vo.FeedVo;
import com.ysm.feed.service.FeedService;
import com.ysm.interaction.api.FollowServiceIRPC;
import com.ysm.item.api.ItemServiceIRPC;
import com.ysm.item.dto.ItemDTO;
import com.ysm.user.api.UserServiceI;
import com.ysm.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@Service
public class FeedServiceImpl implements FeedService {

    @DubboReference
    private UserServiceI userServiceI;

    @DubboReference
    private ItemServiceIRPC itemServiceIRPC;

    @DubboReference
    private FollowServiceIRPC followServiceIRPC;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public List<FeedVo> feed(Long userId, Integer size) {
        ArrayList<FeedVo> feedVos = new ArrayList<>();
        Long lastOffset = (Long) redisTemplate.opsForValue().get(userId.toString()+":feed:"+"offset");
        if (lastOffset == null)lastOffset = 0L;
        Set<ZSetOperations.TypedTuple<Long>> itemSet = new HashSet<>();
        // TODO: 2024/3/4 去自己的收件箱获取id 怎么分页？ 基于时间戳去分页？
        Set<ZSetOperations.TypedTuple<Long>> selfSet = redisTemplate.opsForZSet()
                .rangeByScoreWithScores("feed:inbox:" + userId,  lastOffset+1, System.currentTimeMillis());
        if (selfSet != null && !selfSet.isEmpty()) {
            itemSet.addAll(selfSet);
        }
        // TODO: 2024/3/4 遍历粉丝列表 判断出哪些是大v 是则去发件箱获取
        List<Long> fansId = followServiceIRPC.listSuperId(userId);
        Long finalLastOffset = lastOffset;
        fansId.forEach(id ->{
            Set<ZSetOperations.TypedTuple<Long>> tempSet = redisTemplate.opsForZSet()
                    .rangeByScoreWithScores("feed:outbox:" + id.toString(), finalLastOffset +1, System.currentTimeMillis());
            if (tempSet != null && !tempSet.isEmpty()) {
                itemSet.addAll(tempSet);
            }
        });

        //布隆设置  个人数据量在50万   误判率在1%以下
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(userId + ":feed:" + "bloom");
        bloomFilter.tryInit(500000L,0.01);
        // TODO: 2024/3/4 最后聚合itemId   聚合需求是什么  1.去重  2.布隆过滤  3.分页
        List<ZSetOperations.TypedTuple<Long>> sortSet = itemSet.stream()
                .distinct()
                .filter(longTypedTuple -> !bloomFilter.contains(longTypedTuple.getValue()))  //使用布隆过滤重复itemId
                .sorted((o1, o2) -> (int) (o1.getScore() - o2.getScore()))
                .limit(size)  //分页
                .peek(e -> bloomFilter.add(e.getValue()))  //加入过滤器
                .collect(Collectors.toList());


        if(!sortSet.isEmpty()){
            ZSetOperations.TypedTuple<Long> lastTuple = sortSet.get(sortSet.size() - 1);
            //offset 更新redis
            lastOffset = Objects.requireNonNull(lastTuple.getScore()).longValue();
            redisTemplate.opsForValue().set(userId +":feed:"+"offset",lastOffset);
        }


        // TODO: 2024/3/4 遍历itemId 聚合信息
        sortSet.forEach(e->{
            FeedVo feedVo = new FeedVo();
            Long itemId = e.getValue();
            // TODO: 2024/3/4 获取作者信息
            // TODO: 2024/3/4 获取item详情
            ItemDTO item = itemServiceIRPC.getItem(itemId);
            UserDTO author = userServiceI.getUserDTOById(item.getUserId());
            feedVo.setAuthor(author);
            feedVo.setItem(item);
            // TODO: 2024/3/4 获取统计数据
            // TODO: 2024/3/4 用户存在性判断

            feedVos.add(feedVo);
        });
        // TODO: 2024/3/7 异步mq去删除已读item id
        kafkaTemplate.send("user_inbox_del",userId+":"+lastOffset);




        return feedVos;
    }
}
