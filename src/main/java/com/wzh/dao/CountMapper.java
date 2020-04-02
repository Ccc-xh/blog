package com.wzh.dao;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CountMapper {
    public int getFilesCount();

    public int getZlCOunt();

    public int gettagCount();

    public int getMessage();

    public int getCnzz();
}
