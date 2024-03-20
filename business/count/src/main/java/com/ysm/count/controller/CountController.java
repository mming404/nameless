package com.ysm.count.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.count.entity.bo.LikeBo;
import com.ysm.count.service.CountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/17
 * @Version V1.0
 **/
@RestController
public class CountController {

    @Autowired
    private CountService countService;

    @PostMapping("/")
    public CommonResult<Integer> like(@RequestBody LikeBo likeBo){
        return CommonResult.ok(countService.like(likeBo));
    }
}
