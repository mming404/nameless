package com.ysm.gateway.handler;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.ysm.common.core.domain.CommonResult;
import com.ysm.gateway.entity.LoginBo;
import com.ysm.user.api.UserServiceI;
import com.ysm.user.dto.UserDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/24
 * @Version V1.0
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @DubboReference
    private UserServiceI userServiceI;

    @PostMapping("/login")
    public CommonResult<HashMap<String,Object>> doLogin(LoginBo loginBo) {
        UserDTO userDTO = userServiceI.getUserDTOByLogin(loginBo.getUsername(), loginBo.getPassword());
        System.out.println(userDTO.toString());
        StpUtil.login(123456);
        HashMap<String, Object> hashMap = new HashMap<>();
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        hashMap.put("userInfo",123456);
        hashMap.put("tokenInfo",tokenInfo);
        return CommonResult.ok(hashMap,"登录成功");

    }
}
