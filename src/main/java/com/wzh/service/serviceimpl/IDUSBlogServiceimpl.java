package com.wzh.service.serviceimpl;

import com.wzh.dao.IDUSBlogMapper;
import com.wzh.service.IDUSBlogService;
import com.wzh.vo.ArticleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxh
 * @date 2020/2/27 9:40
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class IDUSBlogServiceimpl implements IDUSBlogService {

    @Autowired
    private IDUSBlogMapper idusBlogMapper;
    private boolean flag = true;

    @Override
    public int updateBlogById(ArticleList articleList) {

        return idusBlogMapper.updateBlogById(articleList);
    }

    @Override
    @Transactional
    public boolean deleteBlogById(ArticleList articleList) {
        //查询分类id
        int sortId = idusBlogMapper.findSortIdByArticleId(articleList);
//        System.out.println("分类id:"+sortId);
        //查询标签id
        int tagsId = idusBlogMapper.findTagIdByArticleId(articleList);
//        System.out.println("标签id:"+tagsId);
        //判断是否要删除分类
        int sotFlag = idusBlogMapper.deleteSortHandler(sortId);
//        System.out.println("是否删除分类:"+sotFlag);
        //判断是否要删除标签
        int tagFlag = idusBlogMapper.deleteTagHandler(tagsId);
//        System.out.println("是否删除标签:"+tagFlag);
        if (sotFlag <= 1) {
            //表示该类下只存在该文章一篇要删除分类
            int i1 = idusBlogMapper.deleteSort(sortId);
//            System.out.println("删除分类:"+i1);
            int i2 = idusBlogMapper.deleteSortArticle(articleList);
//            System.out.println("删除分类关联:"+i2);
            flag = (i1 == 1 && i2 == 1) ? true : false;
        } else {
            int i2 = idusBlogMapper.deleteSortArticle(articleList);
//            System.out.println("删除分类关联:"+i2);
            flag = (i2 == 1) ? true : false;
        }

        if (tagFlag <= 1) {
            int i1 = idusBlogMapper.deleteTag(tagsId);
//            System.out.println("删除标签:"+i1);
            int i2 = idusBlogMapper.deleteTagArticle(articleList);
//            System.out.println("删除标签关联:"+i2);
            flag = (i1 == 1 && i2 == 1) ? true : false;
        } else {
            int i2 = idusBlogMapper.deleteTagArticle(articleList);
//            System.out.println("删除标签关联:" + i2);
            flag = (i2 == 1) ? true : false;
        }
        int i1 = idusBlogMapper.deleteBlogById(articleList);
        return flag && i1 == 1;

    }

}
