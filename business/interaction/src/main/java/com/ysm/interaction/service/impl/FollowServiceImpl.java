package com.ysm.interaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.interaction.api.FollowServiceIRPC;
import com.ysm.interaction.po.Follow;
import com.ysm.interaction.service.FollowService;
import com.ysm.interaction.mapper.FollowMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author 86139
 * @description 针对表【follow】的数据库操作Service实现
 * @createDate 2024-02-28 22:02:33
 */
@Service
@DubboService
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowServiceIRPC, FollowService {

    @Override
    public void follow(Long followerId, Long followingId) {
        Follow follow = new Follow();
        follow.setFollowingId(followingId);
        follow.setFollowerId(followerId);
        save(follow);
    }

    @Override
    public void cancel(Long followerId, Long followingId) {
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getFollowerId, followerId)
                .eq(Follow::getFollowingId, followingId);
        remove(wrapper);
    }

    @Override
    public Long getFollowerCount(Long userId) {
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getFollowingId, userId);
        return count(wrapper);
    }

    @Override
    public List<Long> listFansId(Long userId) {
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getFollowingId, userId);
        return list(wrapper)
                .stream()
                .mapToLong(Follow::getFollowerId)
                .boxed()
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> listSuperId(Long userId) {
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getFollowingId, userId);
        List<Long> collect = list(wrapper)
                .stream()
                .mapToLong(Follow::getFollowerId)
                .boxed()
                .collect(Collectors.toList());
        // TODO: 2024/3/5 待优化 大V判断可以标识出来或者由计数服务计算
        return collect
                .stream()
                .filter(userId1 -> getFollowerCount(userId1) >= 1000L)
                .collect(Collectors.toList());

    }
}




