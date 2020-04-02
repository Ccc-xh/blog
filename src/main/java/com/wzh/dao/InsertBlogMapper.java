package com.wzh.dao;

import com.wzh.vo.ArticleList;
import com.wzh.vo.SortArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface InsertBlogMapper {

    /**
     * 插入文章表
     * @param articleList
     * @return
     */
    public int insertContent(@Param("blog") ArticleList articleList);

    /**
     * 插入分类表
     * @param articleList
     * @return
     */
    public int insertSortName(@Param("blog")ArticleList articleList);

    /**
     * 关联文章和分类表
     * @param articleList
     * @return
     */
    public int coonectionContentAndSortName(@Param("blog")ArticleList articleList);

    /**
     * 判断分类是否存在
     * @param a
     * @return
     */
    public String findSortName(@Param("blog")ArticleList a);

    /**
     * 判断标签是否存在
     */
    public String findTags(@Param("blog")ArticleList articleList);

    /**
     * 插入标签表
     * @param articleList
     * @return
     */
    public int insertTags(@Param("blog")ArticleList articleList);

    /**
     * 关联标签文章
     * @param articleList
     * @return
     */
    public int connectionContentAndTags(@Param("blog")ArticleList articleList);
}
