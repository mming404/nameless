package com.ysm.feed.service;

import com.ysm.feed.entity.vo.FeedVo;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
public interface FeedService {
    List<FeedVo> feed(Long userId, Integer size);
}
