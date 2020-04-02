package com.wzh.vo;

import lombok.Data;

/**
 * @program: rbac_shiro
 * @description:
 * @author: han
 * @create: 2019-12-25 17:37
 * @xgr:
 * @description:
 **/
@Data
public class RolePermission {
    private int id;
    private int roleId;
    private int permissionId;


}
