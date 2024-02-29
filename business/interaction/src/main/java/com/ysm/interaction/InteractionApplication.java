package com.ysm.interaction;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/25
 * @Version V1.0
 **/
@EnableDubbo
@SpringBootApplication(scanBasePackages = {"com.ysm.interaction","com.ysm.common"})
@MapperScan("com.ysm.interaction.mapper")
public class InteractionApplication {
    public static void main(String[] args) {
        SpringApplication.run(InteractionApplication.class,args);
    }
}
