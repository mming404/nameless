package com.ysm.interaction.api;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/29
 * @Version V1.0
 **/
public interface FollowServiceIRPC {

    Long getFollowerCount(Long userId);

    List<Long> listFansId(Long userId);

    List<Long> listSuperId(Long userId);
}
