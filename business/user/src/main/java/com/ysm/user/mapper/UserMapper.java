package com.ysm.user.mapper;

import com.ysm.user.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86139
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-11-01 16:39:06
* @Entity com.ysm.www.po.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




