package com.ysm.feed.entity.vo;

import com.ysm.common.core.utils.bean.BeanUtils;
import com.ysm.count.dto.StatisticsDTO;
import lombok.Data;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/3/4
 * @Version V1.0
 **/
@Data
public class StatisticsVo {
    private Long collectCount;

    private Long commentCount;

    private Long likeCount;

    private Long viewCount;

    public static StatisticsVo getStatisticsVo(StatisticsDTO statisticsDTO){
        StatisticsVo statisticsVo = new StatisticsVo();
        BeanUtils.copyBeanProp(statisticsVo,statisticsDTO);
        return statisticsVo;
    }

}
