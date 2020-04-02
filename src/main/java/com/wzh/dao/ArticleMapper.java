package com.wzh.dao;


import com.wzh.vo.ArticleList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleMapper {
    /**
     * 查询所有的文章
     * @return
     */
    public List<ArticleList> findAll(@Param("topFlag") int topFlag,
                                     @Param("blogTypeId") int blogTypeId,
                                     @Param("randomFlag") int randomFlag,
                                     @Param("startPage") int startPage,
                                     @Param("endPage") int endPage

    );

    /**
     * 根据文章id查询详细文章内容
     * @param id
     * @return
     */
    public ArticleList findById(@Param("contentId")int id);

    public int getCount();
}
