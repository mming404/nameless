package com.ysm.interaction.service;

import com.ysm.interaction.po.Collect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86139
* @description 针对表【collect】的数据库操作Service
* @createDate 2024-02-29 18:25:41
*/
public interface CollectService extends IService<Collect> {

    /**
     * 用户收藏item
     * @param itemId
     * @param userId
     */
    void collect(Long itemId, Long userId);

    /**
     * 取消收藏
     * @param itemId
     * @param userId
     */
    void cancel(Long itemId, Long userId);

    /**
     * 分页获取收藏列表
     * @param current
     * @param userId
     * @return
     */
    List<Long> listCollect(Integer current, Long userId);
}
