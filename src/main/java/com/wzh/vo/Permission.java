package com.wzh.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: rbac_shiro
 * @description:
 * @author: han
 * @create: 2019-12-25 17:36
 * @xgr:
 * @description:
 **/
@Data
public class Permission implements Serializable {

    private int id;
    private String name;
    private String url;


}
