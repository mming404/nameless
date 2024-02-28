package com.ysm.interaction.service;

import com.ysm.interaction.po.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86139
* @description 针对表【comment】的数据库操作Service
* @createDate 2024-02-27 22:08:42
*/
public interface CommentService extends IService<Comment> {


    /**
     * 用户评论
     * @param comment
     */
    void comment(Comment comment);


    /**
     * 分页获取某个Item下的评论
     * @param itemId
     * @return
     */
    List<Comment> getCommentList(Integer itemId,Integer current);
}
