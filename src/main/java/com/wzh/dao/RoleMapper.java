package com.wzh.dao;

import com.wzh.vo.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: rbac_shiro
 * @description:
 * @author: han
 * @create: 2019-12-25 17:44
 * @xgr:
 * @description:
 **/
@Mapper
@Component
public interface RoleMapper {
        @Select("SELECT t.role_id id, t1.`name` NAME,t1.description description FROM user_role t LEFT JOIN role t1  on t.role_id = t1.id WHERE t.user_id = #{userId}")
    @Results(value = {
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "description",column = "description"),
            @Result(property = "permissionList",column = "id",
                    many = @Many(select = "com.wzh.dao.PermissionMapper.findPermissionListByRoleId",fetchType = FetchType.DEFAULT)),
    }
            )
    List<Role> findRoleByUserId(@Param("userId") int userId);
}
