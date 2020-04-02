package com.wzh.dao;

import com.wzh.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegisterMapper {

    public int registerUser(@Param(value = "user")User user);
}
