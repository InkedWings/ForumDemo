package com.example.forumdemo.mapper;

import com.example.forumdemo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    @Insert("insert into user_info (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmt_create}, #{gmt_modified})")
    void insert(User user);
}
