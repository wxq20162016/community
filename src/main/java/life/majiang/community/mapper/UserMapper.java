package life.majiang.community.mapper;


import life.majiang.community.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {


    @Insert("insert into user (name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user  where id=#{id}")
    User findById(@Param("id") Integer id);

    @Select("select * from user where account_id=#{accountId}")
    User findByAccountId(@Param("accountId") String accountId);

    //下面的user表示形参 可以自定义
    //sql语句 前半部分映射数据库原生语句需要和数据库字段对应，后半部分变量解析匹配springboot的驼峰命名规则
    @Update("update user set gmt_modified=#{gmtModified},name=#{name},token=#{token},avatar_url=#{avatarUrl} where id =#{id}")
    void update(User user);

}