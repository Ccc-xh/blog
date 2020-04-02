package com.wzh.service;

import com.wzh.command.ResultCodeInfoEnum;
import com.wzh.vo.User;

public interface RegisterService {
    public ResultCodeInfoEnum registerUser(User user);
}
