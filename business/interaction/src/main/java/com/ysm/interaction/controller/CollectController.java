package com.ysm.interaction.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.interaction.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/29
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/collect")
public class CollectController {


    @Autowired
    private CollectService collectService;

    @PostMapping("/{userId}/{itemId}")
    public CommonResult<String> collect(@PathVariable Long itemId, @PathVariable Long userId){
        collectService.collect(itemId,userId);
        return CommonResult.ok();
    }

    @DeleteMapping("/{userId}/{itemId}")
    public CommonResult<String> cancel(@PathVariable Long itemId, @PathVariable Long userId){
        collectService.cancel(itemId,userId);
        return CommonResult.ok();
    }

    @GetMapping("/list/{userId}")
    public CommonResult<List<Long>> list(@PathVariable Long userId,@RequestParam Integer current){
        return CommonResult.ok(collectService.listCollect(current,userId));
    }

}
