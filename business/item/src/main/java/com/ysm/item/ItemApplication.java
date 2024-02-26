package com.ysm.item;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/2
 * @Version V1.0
 **/
@SpringBootApplication(scanBasePackages = {"com.ysm.item","com.ysm.common"})
@EnableDubbo
@MapperScan("com.ysm.item.mapper")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class,args);
    }
}
