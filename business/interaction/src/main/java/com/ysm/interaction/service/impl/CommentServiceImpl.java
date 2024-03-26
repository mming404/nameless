package com.ysm.interaction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ysm.common.redis.service.RedisService;
import com.ysm.interaction.po.Comment;
import com.ysm.interaction.service.CommentService;
import com.ysm.interaction.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author 86139
 * @description 针对表【comment】的数据库操作Service实现
 * @createDate 2024-02-27 22:08:42
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {


    @Autowired
    private RedisService redisService;

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Override
    public void comment(Comment comment) {
        if (save(comment)){
            // 发送消息队列  异步更新计数
            kafkaTemplate.send("item_count_comment",comment.getItemId()+":1");
        }
    }

    @Override
    public List<Comment> getCommentList(Long itemId, Integer current) {
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        Page<Comment> commentPage = new Page<>(current,20);
        wrapper.eq(Comment::getItemId, itemId)
                .orderByDesc(Comment::getCreatedAt);
        return page(commentPage,wrapper).getRecords();
    }
}




