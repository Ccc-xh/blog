package com.wzh.dao;

import com.wzh.vo.MessageBoard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MessageBoardMapper {

    /**
     * 查询所有留言
     * @return
     */
    public List<MessageBoard> findAllMessage();

    /**
     * 增加留言
     */
    public int addMessage(@Param("messageBoard") MessageBoard messageBoard);


}
