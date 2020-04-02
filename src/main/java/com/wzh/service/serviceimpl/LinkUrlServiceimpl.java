package com.wzh.service.serviceimpl;

import com.wzh.dao.LinkUrlMapper;
import com.wzh.service.LinkUrlService;
import com.wzh.vo.LinkUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenxh
 * @date 2020/2/29 18:32
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class LinkUrlServiceimpl implements LinkUrlService {

    @Autowired
    private LinkUrlMapper linkUrlMapper;
    @Override
    public List<LinkUrl> findAllByUserId(int userId) {
        return linkUrlMapper.findAllByUserId(userId);
    }
}
