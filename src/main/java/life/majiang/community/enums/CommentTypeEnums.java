package life.majiang.community.enums;

public enum  CommentTypeEnums {
    QUESTION(1),
    COMMENT(2);
    private Integer type;

    public Integer getType(){
        return type;
    }
    CommentTypeEnums(Integer type) {
        this.type = type;
    }
}
