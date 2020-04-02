package com.wzh.dao;

import com.wzh.vo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: rbac_shiro
 * @description:
 * @author: han
 * @create: 2019-12-25 17:47
 * @xgr:
 * @description:
 **/
@Mapper
@Component
public interface PermissionMapper {

    @Select("SELECT t4.id id,t4.name name,t4.url url FROM role_permsission t3 LEFT JOIN permission t4 ON t4.id = t3.permission_id WHERE t3.role_id = #{roleId}")
    List<Permission> findPermissionListByRoleId(@Param("roleId") int roleId);
}
