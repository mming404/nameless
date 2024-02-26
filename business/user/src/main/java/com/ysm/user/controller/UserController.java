package com.ysm.user.controller;

import com.alibaba.fastjson2.JSON;
import com.ysm.common.redis.service.RedisService;
import com.ysm.user.po.User;
import com.ysm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/1
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @GetMapping("/testMp")
    public String testMp(){
        User user = new User();

        user.setBirthday(LocalDate.now());


//        user.setId(0L);
        user.setUsername("dwad");
        user.setPassword("dwad");
        user.setNickName("e2q2e");
        user.setPhoneNumber("123123");
        user.setEmail("dwdadw");
        user.setGender((byte)1);
//        user.setDeleted((byte) 0);
        userService.save(user);
//        User user = userService.getById(1719711995323564033L);
//        redisService.setCacheObject("user", user);
//        User user1 = redisService.getCacheObject("user");
//        System.out.println(user1);
//        user.setNickName("ysmhhh");
//        userService.updateById(user);
//        userService.removeById(user);


        return "success";
    }
}
