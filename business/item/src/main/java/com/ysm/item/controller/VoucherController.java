package com.ysm.item.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.item.service.GoodsService;
import com.ysm.item.service.VoucherService;
import org.apache.shenyu.client.springmvc.annotation.ShenyuGetMapping;
import org.apache.shenyu.client.springmvc.annotation.ShenyuSpringMvcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/8
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/voucher")
@ShenyuSpringMvcClient(path = "/api/voucher/**")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @Autowired
    private GoodsService goodsService;


    @GetMapping("/voucherKill")
    @ShenyuGetMapping("/voucherKill")
    public CommonResult<String> voucherKill(@RequestParam Long userId,@RequestParam Long voucherId){
        return voucherService.secKill(userId,voucherId);
    }
}
