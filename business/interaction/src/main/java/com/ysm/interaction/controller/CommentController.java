package com.ysm.interaction.controller;

import com.ysm.common.core.domain.CommonResult;
import com.ysm.interaction.po.Comment;
import com.ysm.interaction.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: TODO
 * @Author MiSinG
 * @Date 2024/2/28
 * @Version V1.0
 **/
@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @PostMapping("/comment")
    public CommonResult<String> comment(@RequestBody Comment comment){
        commentService.comment(comment);
        return CommonResult.ok();
    }

    @GetMapping("/comment")
    public CommonResult<List<Comment>> comment(@RequestParam Long itemId,@RequestParam Integer current){
        return CommonResult.ok(commentService.getCommentList(itemId,current));
    }
}
