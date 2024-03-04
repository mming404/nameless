package com.ysm.feed.entity.vo;

import com.ysm.item.dto.ItemDTO;
import com.ysm.user.dto.UserDTO;
import lombok.Data;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@Data
public class FeedVo {

    private ItemDTO itemDTO;

    private UserDTO userDTO;

    private StatisticsVo statisticsVo;

}
