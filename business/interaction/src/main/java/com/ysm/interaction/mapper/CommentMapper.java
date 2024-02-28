package com.ysm.interaction.mapper;

import com.ysm.interaction.po.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86139
* @description 针对表【comment】的数据库操作Mapper
* @createDate 2024-02-27 22:08:42
* @Entity com.ysm.interaction.po.Comment
*/
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}




