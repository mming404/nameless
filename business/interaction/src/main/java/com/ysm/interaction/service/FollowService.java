package com.ysm.interaction.service;

import com.ysm.interaction.po.Follow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86139
* @description 针对表【follow】的数据库操作Service
* @createDate 2024-02-28 22:02:33
*/
public interface FollowService extends IService<Follow> {


    /**
     * 关注操作
     * @param followerId 关注者
     * @param followingId 被关注者
     */
    void follow(Long followerId, Long followingId);


    /**
     * 取关
     * @param followerId 关注者
     * @param followingId 被关注者
     */
    void cancel(Long followerId, Long followingId);

    /**
     * 获取某人的粉丝数
     * @param userId userId
     * @return 粉丝数
     */
    Long getFollowerCount(Long userId);
}
