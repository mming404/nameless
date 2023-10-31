package com.ysm.consumer;

import com.ysm.entity.User;
import com.ysm.provider.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@Service
public class UserServiceTest {
    @DubboReference
    private UserService userService;

    public void test1(){
        System.out.println(userService.login(new User("1111","ysm666")));
    }
}
