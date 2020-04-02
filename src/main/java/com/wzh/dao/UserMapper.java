package com.wzh.dao;

import com.wzh.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UserMapper {
    /**
     * 查询用户所有信息
     * @param username
     * @return
     */
    public User findAllByUsername(@Param("userName") String username);

    /**
     * 根据用户名查询用于判断用户名是否存在
     */
    public int findUserByUsername(@Param("userName")String userName);

    /**
     * 查询手机号，判断该手机是否注册
     */
    public int findUserByPhone(@Param("phone")String phone);
}
