package com.wzh.service.serviceimpl;

import com.wzh.command.ResultCodeInfoEnum;
import com.wzh.dao.RegisterMapper;
import com.wzh.dao.UserMapper;
import com.wzh.service.RegisterService;
import com.wzh.service.UserService;
import com.wzh.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenxh
 * @date 2020/3/15 22:24
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class RegisterServiceimpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RegisterMapper registerMapper;
    @Override
    public ResultCodeInfoEnum registerUser(User user) {
        System.out.println(user.toString());
        int count = userMapper.findUserByUsername(user.getUsername());
        System.out.println("是否存在用户:"+count);
        if(count != 0){
            return ResultCodeInfoEnum.USERNAME_EXISTS;
        }
        count = userMapper.findUserByPhone(user.getPhone());
        System.out.println("是否存在手机:"+count);
        if(count !=0){
            return ResultCodeInfoEnum.PHONE_EXISTS;
        }
        count = registerMapper.registerUser(user);
        System.out.println("是否注册:"+count);
        if(count ==1){
            return ResultCodeInfoEnum.REGISTER_SUCCESS;
        }
        return ResultCodeInfoEnum.REGISTER_FAIL;
    }
}
