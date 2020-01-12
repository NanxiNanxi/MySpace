package com.nanxi.myspace.myspace.mapper;

import com.nanxi.myspace.myspace.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user(name,account_id,token, gmt_create, gmt_modified) VALUES(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
    @Select("SELECT * FROM USER WHERE token = #{token}")
    User findByToken(@Param("token") String token);//非object的时候需要传入前方的参数
}
