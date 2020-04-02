package com.wzh.service;

import com.wzh.vo.User;

public interface UserService {
    public User findAllByUsername(String username);
}
