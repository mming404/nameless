package com.ysm.interaction.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * 
 * @TableName collect
 */
@TableName(value ="collect")
@Data
public class Collect implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 
     */
    @TableField(value = "item_id")
    private Long itemId;

    /**
     * 
     */
    @TableField(value = "created_at",fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    /**
     * 
     */
    @TableField(value = "updated_at",fill = FieldFill.UPDATE)
    private LocalDateTime updatedAt;

    /**
     * 
     */
    @TableField(value = "deleted")
    @TableLogic(value = "0", delval = "1")
    private Byte deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}