package com.wzh.service;

import com.wzh.vo.ArticleList;
import com.wzh.vo.StyleSort;

import java.util.List;

public interface TagsService {
    public List<StyleSort> findAll();

    public List<ArticleList> findLisByTag(int tagsId);

    public Object selectServcieHandler(int tagsId);
}
