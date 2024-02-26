package com.ysm.item.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName voucher
 */
@TableName(value ="voucher")
@Data
public class Voucher implements Serializable {
    /**
     * 优惠卷 id
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 优惠卷类型
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 优惠卷名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 优惠卷标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 优惠卷详情描述
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 售价，整数方式保存
     */
    @TableField(value = "pay_value")
    private Long payValue;

    /**
     * 抵扣价，整数方式保存
     */
    @TableField(value = "actual_price")
    private Long actualPrice;

    /**
     * 剩余库存
     */
    @TableField(value = "stock")
    private Integer stock;

    /**
     * 状态  0:下架, 1:上架,2过期
     */
    @TableField(value = "voucher_status")
    private Integer voucherStatus;

    /**
     * 过期时间
     */
    @TableField(value = "deadline")
    private LocalDateTime deadline;

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