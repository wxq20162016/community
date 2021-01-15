package life.majiang.community.controller;


import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.ResultDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import life.majiang.community.model.User;
import life.majiang.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller

public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    //使用@RequestBody 接受把前端数据 转化为DTO对象 commentDTO
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request
    ){
        User user =(User) request.getSession().getAttribute("user");
        if(user ==null){
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }
        Comment comment = new Comment();
        //获取前端提交到DTO层，再通过set方法添加到model层，再通过insert提交到数据库
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        //通过系统函数生成固定属性 当前系统时间
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        //获取用户ID从session中获取即可
        comment.setCommentator(user.getId());
        comment.getLikeCount(0L);
        commentService.insert(comment);
        return ResultDTO.okOf();

    }

}
