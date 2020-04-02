package com.wzh.controller;

import com.wzh.command.ResultCodeInfoEnum;
import com.wzh.service.IDUSBlogService;
import com.wzh.service.InsertBlogService;
import com.wzh.service.TagsService;
import com.wzh.vo.ArticleList;
import com.wzh.vo.ResultApi;
import io.swagger.annotations.Api;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenxh
 * @date 2020/2/25 20:33
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@RestController
@RequestMapping("api/authc")
@CrossOrigin("*")
@Api(value = "登录权限接口",tags = "登录权限接口")
public class AuthcController {
        @RequestMapping("check_login")
        public Object checkLogin(){
            return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG,"身份认证成功!");
        }
}

