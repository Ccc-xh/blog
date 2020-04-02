package com.wzh.service;

import com.wzh.vo.ArticleList;
import com.wzh.vo.StyleSort;

import java.util.List;

public interface StyleSortService {

    public List<StyleSort> findAll();

    public List<ArticleList> findLisBySort(String sortName);


    public Object serviceHandler(String sortName);
}
