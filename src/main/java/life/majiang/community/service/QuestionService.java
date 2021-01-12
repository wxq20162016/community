package life.majiang.community.service;


import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=questionMapper.count();
        paginationDTO.setPagination(totalCount, page, size);
        //5*(i-1)  size*(page-1)
        Integer offset=size *(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        //service 层给dto层传数据
        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=questionMapper.countByUserId(userId);
        paginationDTO.setPagination(totalCount, page, size);
        //5*(i-1)  size*(page-1)
        Integer offset=size *(page-1);
        List<Question> questions = questionMapper.listByUserId(userId, offset,size);
        List<QuestionDTO> questionDTOList=new ArrayList<>();

        for (Question question : questions) {
            User user =userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);

        }
        paginationDTO.setQuestions(questionDTOList);
        //service 层给dto层传数据
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question= questionMapper.getById(id);
        QuestionDTO questionDTO=new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        User user=userMapper.findById(question.getCreator());
        //用userMapper的findById方法查出user信息再把user信息 使用set方法存入到questionDTO中 相当于查出一个表的数据 再存入DTO层组合成新的数据集
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            questionMapper.create(question);
        }else{
            //更新
            question.setGmtModified(question.getGmtCreate());
            //controller赋值 service层调用方法
            questionMapper.update(question);
        }
    }
}
