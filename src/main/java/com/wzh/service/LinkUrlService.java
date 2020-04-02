package com.wzh.service;

import com.wzh.vo.LinkUrl;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LinkUrlService {
    public List<LinkUrl> findAllByUserId(int userId);
}
