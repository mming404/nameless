package com.ysm.interaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.interaction.po.Comment;
import com.ysm.interaction.service.CommentService;
import com.ysm.interaction.mapper.CommentMapper;
import org.springframework.stereotype.Service;

/**
* @author 86139
* @description 针对表【comment】的数据库操作Service实现
* @createDate 2024-02-27 22:08:42
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

}




