package com.wzh.vo;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/17 14:44
 * @Description: 用户信息
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Data
@ApiModel(description = "用户登录")
public class User implements Serializable {
    @ApiModelProperty(hidden = true)
    private int id;
    @ApiModelProperty(hidden = true)
    private String nickName;
    @ApiModelProperty(hidden = true)
    private String phone;
    @ApiModelProperty(value = "登录账号", name = "username", required = true, example = "admin")
    private String username;
    @ApiModelProperty(value = "登录密码", name = "password", required = true, example = "123")
    private String password;
    @ApiModelProperty(hidden = true)
    private String gender ;
    @ApiModelProperty(hidden = true)
    private String trueName;
    @ApiModelProperty(hidden = true)
    private String birthday;
    @ApiModelProperty(hidden = true)
    private String email;
    @ApiModelProperty(hidden = true)
    private String personalBrief;
    @ApiModelProperty(hidden = true)
    private String avatarImgUrl;
    @ApiModelProperty(hidden = true)
    private String recentlyLanded;
    @ApiModelProperty(hidden = true)
    private String live;
    @ApiModelProperty(hidden = true)
    private String job;
    @ApiModelProperty(hidden = true)
    private List<Role> roleList;
    @ApiModelProperty(hidden = true)
    private List<Permission> permissionList;

}


