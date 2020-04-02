package com.wzh.dao;

import com.wzh.vo.ArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/29 16:17
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Mapper
@Component
public interface SimpleArticleInfoMapper {

    public List<ArticleList> findAllSimpleInfo(@Param("topFlag") int topFlag, @Param("blogTypeId") int blogTypeId, @Param("randomFlag") int randomFlag);
}
