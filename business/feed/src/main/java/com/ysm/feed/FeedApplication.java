package com.ysm.feed;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/25
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDubbo
public class FeedApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeedApplication.class,args);
    }
}
