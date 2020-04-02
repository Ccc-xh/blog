package com.wzh.service.serviceimpl;

import com.wzh.dao.CountMapper;
import com.wzh.service.CountService;
import com.wzh.vo.Count;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxh
 * @date 2020/3/15 16:19
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service

public class CountServiceimpl implements CountService {
    @Autowired
    private CountMapper countMapper;

    @Override
    public int getFilesCount() {
        return countMapper.getFilesCount();
    }

    @Override
    public int getZlCOunt() {
        return countMapper.getZlCOunt();
    }

    @Override
    public int gettagCount() {
        return countMapper.gettagCount();
    }

    @Override
    public int getMessage() {
        return countMapper.getMessage();
    }
    @Override
    public int cnzzCount() {
        return countMapper.getCnzz();
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Object resultZl(){
        Count count = new Count();
        count.setFileCounts(this.getFilesCount());
        count.setZlCounts(this.getZlCOunt());
        count.setTagsCounts(this.gettagCount());
        count.setMessageCounts(this.getMessage());
        count.setCnzzCounts(this.cnzzCount());
        return count;
    }


}
