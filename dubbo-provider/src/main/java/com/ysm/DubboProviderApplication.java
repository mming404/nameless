package com.ysm;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDubbo
public class DubboProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }
}
