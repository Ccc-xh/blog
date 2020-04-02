package com.wzh.dao;

import com.wzh.vo.ArticleList;
import com.wzh.vo.StyleSort;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TagsMapper {

    public List<StyleSort> findAll();
    public List<ArticleList> findLisByTag(@Param("tagsId") int tagsId);
}
