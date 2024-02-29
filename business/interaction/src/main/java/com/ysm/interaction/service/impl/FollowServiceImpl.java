package com.ysm.interaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.interaction.api.FollowServiceIRPC;
import com.ysm.interaction.po.Follow;
import com.ysm.interaction.service.FollowService;
import com.ysm.interaction.mapper.FollowMapper;
import org.springframework.stereotype.Service;

/**
* @author 86139
* @description 针对表【follow】的数据库操作Service实现
* @createDate 2024-02-28 22:02:33
*/
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService, FollowServiceIRPC {

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
        wrapper.eq(Follow::getFollowerId,followerId)
                .eq(Follow::getFollowingId,followingId);
        remove(wrapper);
    }

    @Override
    public Long getFollowerCount(Long userId) {
        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Follow::getFollowingId,userId);
        return count(wrapper);
    }
}




