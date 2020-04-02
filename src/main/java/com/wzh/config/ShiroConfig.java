package com.wzh.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author chenxh
 * @date 2020/2/24 9:37
 * @Description: shiro配置
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        System.out.println("----------正在加载shiro配置---------");
        ShiroFilterFactoryBean shiroFilterFactoryBean =new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        //没有登录回调
        shiroFilterFactoryBean.setLoginUrl("/api/pub/need_login");
        //登录成功回调
        shiroFilterFactoryBean.setSuccessUrl("/");
        //没有权限回调
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/pub/not_permit");

        //自定义Filter
//        Map<String,Filter>  filterMap = new LinkedHashMap<>();
//        filterMap.put("logout",shiroLogoutFilter());

//        shiroFilterFactoryBean.setFilters(filterMap);
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();


        //匿名访问,游客状态
        filterChainDefinitionMap.put("/api/pub/**","anon");

        //登录可以访问
        filterChainDefinitionMap.put("/api/authc/**","authc");

        //管理员权限
        filterChainDefinitionMap.put("/api/admin/**","roles[admin]");

        //特定权限
        filterChainDefinitionMap.put("/api/update","perms[blog_update]");
        //登出
        filterChainDefinitionMap.put("/api/pub/logout","logout");
        filterChainDefinitionMap.put("/**", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }


    /**
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager (){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //绑定SeesionManager,前后端分离使用
        securityManager.setSessionManager(sessionManager());
        //使用自定义cacheManager
        securityManager.setCacheManager(cacheManager());

        //绑定自定义的登录验证逻辑
        securityManager.setRealm(myRealm());
        return  securityManager;
    }

    @Bean
    public MyRealm myRealm(){
        MyRealm myRealm = new MyRealm();
        return myRealm;
    }
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置散列算法：MD5
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列次数
        credentialsMatcher.setHashIterations(2);

        return credentialsMatcher;
    }

    @Bean
    public SessionManager sessionManager() {
        CustomSessionManager customSessionManager = new CustomSessionManager();
        //超时时间，默认30分钟，会话超时，方法单位为毫秒
        customSessionManager.setGlobalSessionTimeout(15*60*1000);
        //删除失效的session
        customSessionManager.setDeleteInvalidSessions(true);
        // customSessionManager.setSessionFactory(ossSessionFactory());
        //定时检查失效的session
        customSessionManager.setSessionValidationSchedulerEnabled(true);
        //删除url里JSESSIONID
        customSessionManager.setSessionIdUrlRewritingEnabled(false);
        customSessionManager.setSessionIdCookie(simpleCookie());
        customSessionManager.setSessionIdCookieEnabled(true);
        //配置session持久化
        customSessionManager.setSessionDAO(redisSessionDAO());
        return customSessionManager;
    }
    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleSession = new SimpleCookie("token");
        simpleSession.setPath("/");
        simpleSession.setHttpOnly(true);
        return simpleSession;

    }
    /**
     *  配置redis
     */
    @Bean
    public RedisManager getRedisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 配置具体cache实现类
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置缓存时间20S
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

    /**
     * 配置Session持久化
     * @return
     */
    @Bean
    public RedisSessionDAO redisSessionDAO(){
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(getRedisManager());
        //自定义sessionid
        redisSessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());
        return redisSessionDAO;
    }
//    @Bean
//    public MyShiroLogoutFilter shiroLogoutFilter(){
//        MyShiroLogoutFilter shiroLogoutFilter = new MyShiroLogoutFilter();
//
//        return shiroLogoutFilter;
//    }
    /**
     * 管理shiro一些bean的生命周期
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * api controller层面
     * 加入注解的使用，不加入这个AOP注解不生效(shiro的注解，例如：@RequireGuest)
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());;
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 用来扫描上下文妱所有的Advistor(通知器)
     * 将符合条件的Advistor应用到切入点的Bean中，需要在LifecycleBeanPostProcessor创建后才可以创建
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setUsePrefix(true);
        return defaultAdvisorAutoProxyCreator;
    }

}
