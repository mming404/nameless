package com.ysm.item.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ysm.common.core.domain.CommonResult;
import com.ysm.item.po.Voucher;

/**
* @author 86139
* @description 针对表【voucher】的数据库操作Service
* @createDate 2023-11-03 22:03:23
*/
public interface VoucherService extends IService<Voucher> {

    CommonResult<String> secKill(Long userId, Long voucherId);
}
