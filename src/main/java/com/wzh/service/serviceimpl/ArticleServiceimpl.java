package com.wzh.service.serviceimpl;

import com.wzh.dao.ArticleMapper;
import com.wzh.service.ArticleService;
import com.wzh.vo.ArticleList;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxh
 * @date 2020/2/24 11:18
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class ArticleServiceimpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Object findAll(int topFlag,int blogTypeId,int randomFlag,int startPage,int endPage) {
        System.out.println("page:"+startPage+",page:"+endPage);
        Map<String,Object> map = new HashMap<>();
        map.put("count",articleMapper.getCount());
        map.put("result",articleMapper.findAll(topFlag,blogTypeId,randomFlag,startPage,endPage));
        return map;
    }

    @Override
    public ArticleList findById( int id) {

        return articleMapper.findById(id);
    }

    @Override
    public int getCount() {
        return articleMapper.getCount();
    }

    @Override
    public Object selectDaoById(int id,int topFlag,int blogTypeId,int randomFlag,int startPage,int endPage) {
        if(-1 == id){
            return this.findAll(topFlag,blogTypeId,randomFlag,startPage,endPage);
        }
       return this.findById(id);
    }


}
