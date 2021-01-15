package life.majiang.community.controller;


import life.majiang.community.dto.CommentDTO;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller

public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    //使用@RequestBody 接受把前端数据 转化为DTO对象 commentDTO
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        //获取前端提交到DTO层，再通过set方法添加到model层，再通过insert提交到数据库
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        //通过系统函数生成固定属性 当前系统时间
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(System.currentTimeMillis());
        //获取用户ID从session中获取即可
        comment.setCommentator(1);
        comment.getLikeCount(0L);
        commentMapper.insert(comment);
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("message","成功");

        return objectObjectHashMap;

    }

}
