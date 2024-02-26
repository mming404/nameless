package com.ysm.item.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName goods
 */
@TableName(value ="goods")
@Data
public class Goods implements Serializable {
    /**
     * goods id
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类ID
     */
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 所属用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 商品名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 商品标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 商品详情描述
     */
    @TableField(value = "detail")
    private String detail;

    /**
     * 商品介绍主图
     */
    @TableField(value = "main_img_url")
    private String mainImgUrl;

    /**
     * 商品图片 多个图片逗号分隔
     */
    @TableField(value = "img_urls")
    private String imgUrls;

    /**
     * 商品视频
     */
    @TableField(value = "video_url")
    private String videoUrl;

    /**
     * 售价，整数方式保存
     */
    @TableField(value = "price")
    private Long price;

    /**
     * 状态  0:下架, 1:上架
     */
    @TableField(value = "status")
    private Byte status;

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