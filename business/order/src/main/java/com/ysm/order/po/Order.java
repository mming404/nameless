package com.ysm.order.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName order
 */
@TableName(value ="t_order")
@Data
public class Order implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type =  IdType.ASSIGN_ID)
    private Long id;

    /**
     * 买家id
     */
    @TableField(value = "customer_id")
    private Long customerId;

    /**
     * 卖家id
     */
    @TableField(value = "seller_id")
    private Long sellerId;

    /**
     * 订单标题
     */
    @TableField(value = "order_title")
    private String orderTitle;

    /**
     * 订单详情
     */
    @TableField(value = "order_detail")
    private String orderDetail;

    /**
     * 商品id
     */
    @TableField(value = "good_id")
    private Long goodId;

    /**
     * 商品总数
     */
    @TableField(value = "good_nums")
    private Integer goodNums;

    /**
     * 订单总价
     */
    @TableField(value = "total_money")
    private Long totalMoney;

    /**
     * 状态  0:未支付, 1:已支付
     */
    @TableField(value = "pay")
    private Byte pay;

    /**
     * 创建时间 自动填充
     */
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 更新时间 自动填充
     */
    @TableField(value = "updated_at",fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 逻辑删除
     */
    @TableField(value = "deleted")
    @TableLogic(value = "0", delval = "1")
    private Byte deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}