package life.majiang.community.dto;

import life.majiang.community.model.User;
import lombok.Data;

@Data
public class QuestionDTO {
    private String title;
    private Integer id;
    private String description;
    private Long gmt_create;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private String avatarUrl;
    private User user;
}
