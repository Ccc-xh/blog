package com.wzh.service;

import com.wzh.vo.MessageBoard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageBoardService {
    /**
     * 查询所有留言
     * @return
     */
    public List<MessageBoard> findAllMessage();

    /**
     * 增加留言
     */
    public int addMessage(@Param("messageBoard") MessageBoard messageBoard);

    public Object ServiceHnalder(MessageBoard messageBoard,int type);
}
