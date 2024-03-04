package com.ysm.item.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@Data
public class ItemDTO implements Serializable {
    /**
     *
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 视频url
     */
    private String videoUrl;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     *
     */
    private LocalDateTime createdAt;

    /**
     *
     */
    private LocalDateTime updatedAt;

    /**
     * 0 私密 1公开
     */
    private Byte status;

    /**
     * 逻辑删除
     */
    private Byte deleted;

    private static final long serialVersionUID = 1L;
}
