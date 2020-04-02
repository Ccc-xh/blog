package com.wzh.config;

import com.wzh.service.UserService;
import com.wzh.vo.Permission;
import com.wzh.vo.Role;
import com.wzh.vo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/24 9:50
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    /**
     * 授权管理
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("===========授权认证=============");
        User user = (User) principalCollection.getPrimaryPrincipal();
        User myuser = userService.findAllByUsername(user.getUsername());
        List<String> stringRoleList = new ArrayList<>();
        List<String> stringpermssionList = new ArrayList<>();
        List<Role> roleList =myuser.getRoleList();
        for(Role role : roleList ) {
            stringRoleList.add(role.getName());
            List<Permission> permissionList = role.getPermissionList();
            for(Permission permission: permissionList){
               if(permission != null){
                   stringpermssionList.add(permission.getName());
               }
            }
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringpermssionList);
        return simpleAuthorizationInfo;
    }

    /**
     * 登录管理
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("--------认证登录----------");
        String userName = (String)authenticationToken.getPrincipal();
//       根据用户密码查询
        User user = userService.findAllByUsername(userName);
        String pwd = user.getPassword();
        if(pwd == null || "".equals(pwd)){
            return  null;
        }
         return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
