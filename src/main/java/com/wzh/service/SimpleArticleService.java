package com.wzh.service;

import com.wzh.vo.ArticleList;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/29 16:19
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
public interface SimpleArticleService {
    public List<ArticleList> findAllSimpleInfo(int topFlag,int blogTypeId,int randomFlag );
}
