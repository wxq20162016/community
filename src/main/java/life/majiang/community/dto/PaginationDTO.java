package life.majiang.community.dto;

import life.majiang.community.model.Question;
import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private boolean page;
    private List<Integer> pages;


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        //totalCount/size;
        Integer totalPage;
        if(totalCount % size ==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        //是否展示上一页
        if(page==1){
            showFirstPage=false;
        }else {
            showFirstPage=true;
        }

        //是否展示下一页
        if(page==totalPage){
            showEndPage=false;
        }else{
            showEndPage=true;
        }
    }
}
