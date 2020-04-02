package com.wzh.service.serviceimpl;

import com.wzh.dao.InsertBlogMapper;
import com.wzh.service.InsertBlogService;
import com.wzh.vo.ArticleList;
import com.wzh.vo.SortArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author chenxh
 * @date 2020/2/26 15:14
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class InsertBlogServiceimpl implements InsertBlogService {

    @Autowired
    private InsertBlogMapper insertBlogMapper;

    private boolean insertBlogFlag = true;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBlog(ArticleList articleList) {

        if (insertBlogMapper.insertContent(articleList) != 1) {
            insertBlogFlag = false;
        }
        //判断此分类是否存在,不存在则创建一个
        String str = insertBlogMapper.findSortName(articleList);
        //判断此标签是否存在
        String str1 = insertBlogMapper.findTags(articleList);
        if(str1 != null){
            insertBlogMapper.connectionContentAndTags(articleList);
        }else {
            if(insertBlogMapper.insertTags(articleList)!=1||
                insertBlogMapper.connectionContentAndTags(articleList)!=1
            ){
                insertBlogFlag = false;
            }
        }
        if (str != null) {
            insertBlogMapper.coonectionContentAndSortName(articleList);
        } else {
            if (insertBlogMapper.insertSortName(articleList) != 1  ||
                    insertBlogMapper.coonectionContentAndSortName(articleList) != 1) {
                insertBlogFlag = false;
            }
        }
        return insertBlogFlag == true ? 1 : 0;
    }
}
