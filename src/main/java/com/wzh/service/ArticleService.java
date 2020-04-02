package com.wzh.service;

import com.wzh.vo.ArticleList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleService {
    /**
     * 查询所有的文章
     * @return
     */
    public Object findAll(int topFlag,int blogTypeId,int randomFlag,int startPage,int endPage);

    /**
     * 根据文章id查询详细文章内容
     * @param id
     * @return
     */
    public ArticleList findById(int id);

    public int getCount();

    public Object selectDaoById(int id,int topFlag,int blogTypeId,int randomFlag,int startPage,int endPage);
}
