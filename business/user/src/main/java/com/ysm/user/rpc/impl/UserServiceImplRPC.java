package com.ysm.user.rpc.impl;

import com.ysm.common.core.utils.bean.BeanUtils;
import com.ysm.user.api.UserServiceI;
import com.ysm.user.dto.UserDTO;
import com.ysm.user.po.User;
import com.ysm.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/8
 * @Version V1.0
 **/
@Slf4j
@DubboService
public class UserServiceImplRPC implements UserServiceI {

    @Autowired
    private UserService userService;

    @Override
    public UserDTO getUserDTOById(Long id) {
        User user = userService.getById(id);
        if(user == null){
            log.error("rpc用户不存在");
            return null;
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyBeanProp(userDTO,user);
        return userDTO;
    }

    @Override
    public UserDTO getUserDTOByLogin(String username, String password) {
        User user = userService.login(username, password);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyBeanProp(userDTO,user);
        return userDTO;
    }
}
