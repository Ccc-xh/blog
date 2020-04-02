package com.wzh.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: rbac_shiro
 * @description:
 * @author: han
 * @create: 2019-12-25 17:36
 * @xgr:
 * @description:
 **/
@Data
public class Role implements Serializable {

    private int id;
    private String name;
    private String description;

    private List<Permission> permissionList;

    public List<Permission> getPermissionList() {
        return permissionList;
    }




}
