package com.wzh.service.serviceimpl;

import com.wzh.dao.SimpleArticleInfoMapper;
import com.wzh.service.SimpleArticleService;
import com.wzh.vo.ArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/29 16:19
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class SimpleArticleServiceimpl implements SimpleArticleService {

    @Autowired
    private SimpleArticleInfoMapper simpleArticleInfoMapper;
    @Override
    public List<ArticleList> findAllSimpleInfo(int topFlag,int blogTypeId,int randomFlag) {
        return simpleArticleInfoMapper.findAllSimpleInfo(topFlag,blogTypeId,randomFlag);
    }
}
