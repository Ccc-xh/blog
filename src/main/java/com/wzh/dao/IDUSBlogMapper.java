package com.wzh.dao;

import com.wzh.vo.ArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface IDUSBlogMapper {

    /**
     * 根据id更新文章,目前支持修改文章表的内容
     * @param articleList
     * @return
     */
    public int updateBlogById(@Param(value = "blog") ArticleList articleList);

    /**
     * 删除文章,根据id
     * @param articleList
     * @return
     */
    public  int deleteBlogById(@Param(value = "blog")ArticleList articleList);

    /**
     * 如果被删除的文章分类下的文章数为0 则删除该分类
     * @param sortId
     * @return
     */
    public int deleteSortHandler(@Param(value = "sortId")int sortId);

    /**
     * 删除关联数据
     * @param articleList
     * @return
     */
    public  int deleteSortArticle(@Param(value="blog") ArticleList articleList);

    /**
     * 删除分类
     * @param
     * @return
     */
    public int deleteSort(@Param(value = "sortId")int id);

    /**
     * 查询分类id
     */
    public int findSortIdByArticleId(@Param(value = "blog") ArticleList articleList);

    /**
     * 查询标签id
     * @param articleList
     * @return
     */
    public int findTagIdByArticleId(@Param(value = "blog")ArticleList articleList);

    /**
     * 如果标签下文章是0则删除该标签
     * @param tagsId
     * @return
     */
    public int deleteTagHandler(@Param(value = "tagsId")int tagsId);

    /**
     * 删除关联标签数据
     * @param articleList
     * @return
     */
    public int deleteTagArticle(@Param(value = "blog")ArticleList articleList);

    /**
     * 删除标签
     * @param id
     * @return
     */
    public int deleteTag(@Param(value = "tagId")int id);
}
