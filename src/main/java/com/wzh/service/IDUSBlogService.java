package com.wzh.service;

import com.wzh.vo.ArticleList;

public interface IDUSBlogService {

    /**
     * 根据id更新文章,目前支持修改文章表的内容
     * @param articleList
     * @return
     */
    public int updateBlogById(ArticleList articleList);

    /**
     * 删除文章,根据id
     * @param articleList
     * @return
     */
    public  boolean deleteBlogById(ArticleList articleList);
}
