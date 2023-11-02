package com.ysm.goods.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 分类ID
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 父分类ID
     */
    @TableField(value = "parent_category_id")
    private Integer parentCategoryId;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
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