package com.ysm.order.bo;

import lombok.Data;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2023/11/29
 * @Version V1.0
 **/
@Data
public class OrderCreateBo {


    /**
     * 买家id
     */
    private Long customerId;

    /**
     * 卖家id
     */
    private Long sellerId;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 订单详情
     */
    private String orderDetail;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品总数
     */
    private Integer goodNums;

    /**
     * 订单总价
     */
    private Long totalMoney;

    /**
     * 状态  0:未支付, 1:已支付
     */
    private Byte pay;

}
