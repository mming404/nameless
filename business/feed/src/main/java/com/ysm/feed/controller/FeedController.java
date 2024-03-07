package com.ysm.feed.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.feed.entity.vo.FeedVo;
import com.ysm.feed.service.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@RestController
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/{userId}")
    public CommonResult<List<FeedVo>> feed(@PathVariable Long userId, @RequestParam(required = false,defaultValue = "6") Integer size){
        return CommonResult.ok(feedService.feed(userId,size));
    }
}
