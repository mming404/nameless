package com.ysm.goods.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.goods.po.Goods;
import com.ysm.goods.service.GoodsService;
import com.ysm.goods.mapper.GoodsMapper;
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




