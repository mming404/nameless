package com.ysm.interaction.rpc;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.ysm.interaction.api.FollowServiceIRPC;
import com.ysm.interaction.po.Follow;
import com.ysm.interaction.service.FollowService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/2
 * @Version V1.0
 **/
//@DubboService
//@Component
//public class FollowServiceRPC implements FollowServiceIRPC{
//
//    @Autowired
//    private FollowService followService;
//
//    @Override
//    public Long getFollowerCount(Long userId) {
//        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Follow::getFollowingId,userId);
//        return followService.count(wrapper);
//    }
//
//    @Override
//    public List<Long> listFansId(Long userId) {
//        LambdaQueryWrapper<Follow> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(Follow::getFollowingId,userId);
//        return followService.list(wrapper)
//                .stream()
//                .mapToLong(Follow::getFollowerId)
//                .boxed()
//                .collect(Collectors.toList());
//    }
//}
