package com.ysm.count.service.impl;

import com.ysm.count.batch.BatchUtil;
import com.ysm.count.entity.bo.LikeBo;
import com.ysm.count.service.CountService;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/17
 * @Version V1.0
 **/
@Service
public class CountServiceImpl implements CountService {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    private BatchUtil<Integer, Integer> batchUtil;

    @PostConstruct
    private void postConstructorInit() {
        // 当请求数量达到20个，或每过5s合并执行一次请求
        batchUtil = BatchUtil.getInstance((input, handlerType) -> {
            System.out.println("处理类型:" + handlerType + ",接受到批量请求参数:" + input);
            return input.stream().mapToInt(x -> x).sum();
        }, 100, 5);
    }

    @Override
    public Integer like(LikeBo likeBo) {
        // TODO: 2024/3/17 先布隆判断时候已经点赞
        RBloomFilter<Object> bloomFilter = redissonClient.getBloomFilter(likeBo.getUserId() + ":like:" + "bloom");
        bloomFilter.tryInit(1000L,0.01);
        // TODO: 2024/3/17 如果布隆过滤器 发生误判 怎么进行兜底而不影响性能 ？
        //判断点赞还是取消   进行累加/累减
        if(likeBo.getType() == 1 && !bloomFilter.contains(likeBo.getItemId())){
            //点赞
            batchUtil.addRequestParam(1);
            return 1;
        }else if (likeBo.getType() == 0){
            //取消点赞
            batchUtil.addRequestParam(-1);
            return 0;
        }
        //兜底
        return null;
        //定时写入redis 计数
    }
}
