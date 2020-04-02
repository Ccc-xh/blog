package com.wzh.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author chenxh
 * @date 2020/3/7 21:50
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
public class MyShiroLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        ///在这里执行退出系统前需要清空的数据
        Subject subject = getSubject(request, response);

//        String redirectUrl = getRedirectUrl(request, response, subject);

        try {

            subject.logout();

        } catch (Exception ise) {

            ise.printStackTrace();

        }

//        issueRedirect(request, response, redirectUrl);
        return false;

    }
}
