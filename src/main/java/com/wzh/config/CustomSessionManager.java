package com.wzh.config;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * @program: springboot_shiro
 * @description: 自定义session
 * @author: han
 * @create: 2019-12-27 16:52
 * @xgr:
 * @description:
 **/
public class CustomSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "Authorization";

    public CustomSessionManager(){
        super();
    }



    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response){
        //将ServletRequest转换为HTTPServletReqeust并且将token设置到Headers中
        String sessionid = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if(sessionid != null){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,ShiroHttpServletRequest.COOKIE_SESSION_ID_SOURCE);
            //判断sessionid是否有效，过期等
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionid);
            //标记session为有效
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return sessionid;
        }else {
            return super.getSessionId(request,response);
        }
    }
}
