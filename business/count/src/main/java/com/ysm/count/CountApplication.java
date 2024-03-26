package com.ysm.count;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/17
 * @Version V1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.ysm.count","com.ysm.common"})
@EnableDubbo
@ComponentScan(value = {"com.ysm.count", "com.ysm.common"})
public class CountApplication {
    public static void main(String[] args) {
        SpringApplication.run(CountApplication.class,args);
    }
}
