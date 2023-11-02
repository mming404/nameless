package com.ysm.user;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/1
 * @Version V1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.ysm.user","com.ysm.common"})
@EnableDubbo
@MapperScan("com.ysm.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
