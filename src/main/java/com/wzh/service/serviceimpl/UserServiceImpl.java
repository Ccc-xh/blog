package com.wzh.service.serviceimpl;

import com.wzh.dao.PermissionMapper;
import com.wzh.dao.RoleMapper;
import com.wzh.dao.UserMapper;
import com.wzh.service.UserService;
import com.wzh.vo.Permission;
import com.wzh.vo.Role;
import com.wzh.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/17 15:20
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User findAllByUsername(String userName) {
        User  user = userMapper.findAllByUsername(userName);
        List<Role>  roleList = roleMapper.findRoleByUserId(user.getId());
        user.setRoleList(roleList);
        return user;
    }
}
