package com.ysm.count.service;

import com.ysm.count.entity.bo.LikeBo;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/17
 * @Version V1.0
 **/
public interface CountService {

    //用户点赞
    Integer like(LikeBo likeBo);
}
