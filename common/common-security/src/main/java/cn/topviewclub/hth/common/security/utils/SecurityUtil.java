package cn.topviewclub.hth.common.security.utils;

import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/10/15
 * @Version V1.0
 **/
public class SecurityUtil {

    @Resource
    private PasswordEncoder passwordEncoder;

    public String saltEncryption(String original,String salt){
        return passwordEncoder.encode(original + salt);
    }
}
