package com.wzh.dao;


import com.wzh.vo.LinkUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LinkUrlMapper {

    public List<LinkUrl>  findAllByUserId(@Param("userId")int userId);
}
