package com.ysm;

import com.ysm.consumer.UserServiceTest;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/9/24
 * @Version V1.0
 **/
@SpringBootApplication
@EnableDubbo
public class DubboConsumerApplication {
    public static void main(String[] args){
        SpringApplication.run(DubboConsumerApplication.class,args);
    }

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("topic1")
                .partitions(10)
                .build();
    }
}
