package com.wzh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CnzzMapper {

    public int update();
}
