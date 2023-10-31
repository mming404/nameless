package com.ysm.provider;

import com.ysm.entity.User;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@DubboService
public class UserServiceImpl implements UserService{
    @Override
    public boolean login(User user) {
        System.out.println("username = " + user.getUsername());
        System.out.println("password = " + user.getPassword());
        System.out.println("登录成功");
        return true;
    }
}
