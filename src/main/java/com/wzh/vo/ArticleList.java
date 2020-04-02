package com.wzh.vo;

import lombok.Data;

/**
 * @author chenxh
 * @date 2020/2/24 10:50
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Data
public class ArticleList {
    private String articleId;
    private String author;
    private String articleTitle;
    private String articleContent;
    private String articleMdContent;
    private String publishDate;
    private String isOriginal;
    private String articleUrl;
    private String tagsName;
    private String viewNum;
    private String articleImg;
    private String sortName;
    private int topFlag;
    private int sortFId;



}
