package com.wzh.controller;

import com.wzh.command.ResultCodeInfoEnum;
import com.wzh.service.IDUSBlogService;
import com.wzh.service.InsertBlogService;
import com.wzh.vo.ArticleList;
import com.wzh.vo.ResultApi;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenxh
 * @date 2020/3/5 16:15
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@RestController
@RequestMapping("api/admin")
@CrossOrigin("*")
@Api(value = "root用户接口",tags = "root用户接口")
public class AdminController {

    @Autowired
    private InsertBlogService insertBlogService;

    @Autowired
    private IDUSBlogService idusBlogService;


    @RequestMapping("check_login_root.do")
    public Object test(HttpServletRequest request, HttpServletResponse response){
        System.out.println("reroot:"+request.getHeader("Authorization"));
        return ResultApi.ResultAll(ResultCodeInfoEnum.SUCCESS_MSG,true);
    }

    @PostMapping("insert.do")
    public Object insertBlog(@RequestBody ArticleList articleList

    ) {
        int flag = insertBlogService.insertBlog(articleList);
        if (flag == 1) {
            return ResultApi.ResultNoAndDesc(ResultCodeInfoEnum.INSERT_SUCCESS, true);
        } else {
            return ResultApi.ResultNoAndDesc(ResultCodeInfoEnum.INSERT_FAIL, true);
        }
    }

    @PostMapping(value = "update.do")
    public Object updateBlog(@RequestBody ArticleList articleList, @RequestParam(name = "type") int type) {
        ResultCodeInfoEnum anEnum;
        if (type == 1) {
            //更新文章
            int updateFlag = idusBlogService.updateBlogById(articleList);
            anEnum = updateFlag == 1 ?
                    ResultCodeInfoEnum.UPDATE_SUCCESS : ResultCodeInfoEnum.UPDATE_FAIL;
            return ResultApi.ResultNoAndDesc(anEnum, true);
        }if(type ==2){
            //删除操作
            boolean delFlag = idusBlogService.deleteBlogById(articleList);
//            System.out.println("删除:"+delFlag);

            anEnum =  delFlag == true?
                    ResultCodeInfoEnum.DEL_SUCCESS:ResultCodeInfoEnum.DEL_FAIL;
            return ResultApi.ResultNoAndDesc(anEnum,true);
        }

        return 0;
    }
}
