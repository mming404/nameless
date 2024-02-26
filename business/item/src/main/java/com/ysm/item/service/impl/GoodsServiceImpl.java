package com.ysm.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.item.po.Goods;
import com.ysm.item.service.GoodsService;
import com.ysm.item.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86139
* @description 针对表【goods】的数据库操作Service实现
* @createDate 2023-11-02 11:39:42
*/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods>
    implements GoodsService{

}




