package com.ysm.interaction.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.interaction.service.FollowService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/29
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @PostMapping("/{followerId}/{followingId}")
    public CommonResult<String> follow(@PathVariable Long followerId, @PathVariable Long followingId){
        followService.follow(followerId,followingId);
        return CommonResult.ok();
    }

    @DeleteMapping("/{followerId}/{followingId}")
    public CommonResult<String> cancelFollow(@PathVariable Long followerId, @PathVariable Long followingId){
        followService.cancel(followerId,followingId);
        return CommonResult.ok();
    }

    @GetMapping("/count/{userId}")
    public CommonResult<Long> getFollowerCount(@PathVariable Long userId){
        return CommonResult.ok(followService.getFollowerCount(userId));
    }
}
