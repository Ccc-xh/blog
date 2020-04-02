package com.wzh.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @program: rbac_shiro
 * @description: 自定义生成SessionId
 * @author: han
 * @create: 2019-12-27 14:36
 * @xgr:
 * @description:
 **/
public class CustomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {

        return "com.blog"+UUID.randomUUID().toString().replace("-","");
    }
}
