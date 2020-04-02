package com.wzh.service.serviceimpl;

import com.wzh.dao.StyleSortMapper;
import com.wzh.service.StyleSortService;
import com.wzh.vo.ArticleList;
import com.wzh.vo.StyleSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/25 8:51
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class StyleSortServiceimpl implements StyleSortService {
    @Autowired
    private StyleSortMapper styleSortMapper;
    @Override
    public List<StyleSort> findAll() {


        return styleSortMapper.findAll();
    }

    @Override
    public List<ArticleList> findLisBySort(String sortName) {
        return styleSortMapper.findLisBySort(sortName);
    }

    @Override
    public Object serviceHandler(String sortName) {
        if("false".equals(sortName)){
           return this.findAll();
        }
        return this.findLisBySort(sortName);
    }
}
