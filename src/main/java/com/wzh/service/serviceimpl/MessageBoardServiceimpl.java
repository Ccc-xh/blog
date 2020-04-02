package com.wzh.service.serviceimpl;

import com.wzh.dao.MessageBoardMapper;
import com.wzh.service.MessageBoardService;
import com.wzh.vo.MessageBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author chenxh
 * @date 2020/3/3 19:30
 * @Description:
 * @modify:
 * @modifyDate:
 * @Description:
 */
@Service
public class MessageBoardServiceimpl implements MessageBoardService {

    @Autowired
    private MessageBoardMapper  messageBoardMapper;

    @Override
    public List<MessageBoard> findAllMessage() {
        return messageBoardMapper.findAllMessage();
    }

    @Override
    public int addMessage(MessageBoard messageBoard) {

        return messageBoardMapper.addMessage(messageBoard);
    }

    @Override
    public Object ServiceHnalder( MessageBoard messageBoard, int type) {

        if(type == 0){
            return  this.findAllMessage();
        }else {
            return addMessage(messageBoard);
        }
    }
}
