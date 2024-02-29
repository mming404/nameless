package com.ysm.interaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.interaction.po.Collect;
import com.ysm.interaction.service.CollectService;
import com.ysm.interaction.mapper.CollectMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
* @author 86139
* @description 针对表【collect】的数据库操作Service实现
* @createDate 2024-02-29 18:25:41
*/
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect>
    implements CollectService{

    @Override
    public void collect(Long itemId, Long userId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setItemId(itemId);
        save(collect);
    }

    @Override
    public void cancel(Long itemId, Long userId) {
        LambdaQueryWrapper<Collect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Collect::getUserId,userId)
                .eq(Collect::getItemId,itemId);
        remove(wrapper);
    }

    @Override
    public List<Long> listCollect(Integer current, Long userId) {
        Page<Collect> collectPage = new Page<>(current,20);
        LambdaQueryWrapper<Collect> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(Collect::getId,Collect::getItemId)
                .eq(Collect::getUserId,userId)
                .orderByDesc(Collect::getCreatedAt);
        return page(collectPage,wrapper)
                .getRecords()
                .stream()
                .flatMapToLong(collect -> LongStream.of(collect.getId()))
                .boxed()
                .collect(Collectors.toList());
    }
}




