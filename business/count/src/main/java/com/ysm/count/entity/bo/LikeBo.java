package com.ysm.count.entity.bo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/17
 * @Version V1.0
 **/
@Data
public class LikeBo {

    private Long itemId;

    private Long userId;

    private Integer type;
}
