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
}
