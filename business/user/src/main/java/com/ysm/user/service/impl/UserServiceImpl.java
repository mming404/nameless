package com.ysm.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.user.mapper.UserMapper;
import com.ysm.user.service.UserService;
import com.ysm.user.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
* @author 86139
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-11-01 16:39:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




