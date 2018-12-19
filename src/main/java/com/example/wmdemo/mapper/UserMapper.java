package com.example.wmdemo.mapper;


import com.example.wmdemo.util.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findUserById(Integer id);
}