package com.ysm.item.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.item.po.Category;
import com.ysm.item.service.CategoryService;
import com.ysm.item.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

/**
* @author 86139
* @description 针对表【category】的数据库操作Service实现
* @createDate 2023-11-02 11:39:42
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

}




