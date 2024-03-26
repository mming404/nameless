package com.ysm.count.api;

import com.ysm.count.dto.StatisticsDTO;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/22
 * @Version V1.0
 **/
public interface CountServiceIRPC {

    /**
     * 根据id获取点赞数
     * @return Long
     */
    Long getLikeCountByItemId(Long itemId);

    /**
     * 根据id获取收藏数
     * @return Long
     */
    Long getCollectCountByItemId(Long itemId);

    /**
     * 根据id获取评论数
     * @return Long
     */
    Long getCommentCountByItemId(Long itemId);

    /**
     * 根据id获取浏览量
     * @return Long
     */
    Long getViewCountByItemId(Long itemId);

    StatisticsDTO getStatisticsDTO(Long itemId);
}
