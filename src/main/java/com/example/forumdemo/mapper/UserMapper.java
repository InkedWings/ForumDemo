package com.example.forumdemo.mapper;

import com.example.forumdemo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user_info (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmt_create}, #{gmt_modified})")
    void insert(User user);

    @Select("select * from user_info where token = #{token}")
    User findByToken(@Param("token") String token);
}
