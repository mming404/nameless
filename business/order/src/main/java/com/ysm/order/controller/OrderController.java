package com.ysm.order.controller;

import com.ysm.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/29
 * @Version V1.0
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/test")
    public String test(){
        orderService.testShardingJDBC();
        return "success";
    }
}
