package com.wzh.service.serviceimpl;

import com.wzh.dao.TagsMapper;
import com.wzh.service.TagsService;
import com.wzh.vo.ArticleList;
import com.wzh.vo.StyleSort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/28 9:42
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class TagsServiceimpl implements TagsService {

   @Autowired
   private TagsMapper tagsMapper;
    @Override
    public List<StyleSort> findAll() {
        return tagsMapper.findAll();
    }

    @Override
    public List<ArticleList> findLisByTag(int tagsId) {

        return tagsMapper.findLisByTag(tagsId);
    }
    @Override
    public Object selectServcieHandler(int tagsId){
        if(tagsId == -1){
            return this.tagsMapper.findAll();
        }else {
            return this.tagsMapper.findLisByTag(tagsId);
        }
    }
}
