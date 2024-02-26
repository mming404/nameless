package com.ysm.item.po;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @TableName item
 */
@TableName(value ="item")
@Data
public class Item implements Serializable {
    /**
     * 
     */
    @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 标题
     */
    @TableField(value = "title")
    private String title;

    /**
     * 视频url
     */
    @TableField(value = "video_url")
    private String videoUrl;

    /**
     * 点赞数
     */
    @TableField(value = "like_num")
    private Long likeNum;

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
     * 0 私密 1公开
     */
    @TableField(value = "status")
    private Byte status;

    /**
     * 逻辑删除
     */
//    @TableField(value = "deleted")
    @TableLogic(value = "0", delval = "1")
    private Byte deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}