package com.system.exam.service.message.impl;

import com.system.exam.domain.dto.message.MessageCountDTO;
import com.system.exam.domain.dto.message.MessageDTO;
import com.system.exam.domain.qo.message.MessageCountQO;
import com.system.exam.domain.qo.message.MessageQO;
import com.system.exam.mapper.message.MessageMapper;
import com.system.exam.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;

    /**
     * 用户消息条数
     * @param messageCountQO
     * @return
     */
    @Override
    public MessageCountDTO getMessageCount(MessageCountQO messageCountQO) {
        if (messageCountQO.getNumber()==null || "".equals(messageCountQO.getNumber())) {
            return new MessageCountDTO();
        } else {
            return messageMapper.getMessageCount(messageCountQO);
        }
    }

    /**
     * 分页查询新消息
     * @param messageQO
     * @return
     */
    @Override
    public List<MessageDTO> pageNewMessage(MessageQO messageQO) {
        return messageMapper.pageNewMessage(messageQO);
    }

    /**
     * 分页查询已发消息
     * @param messageQO
     * @return
     */
    @Override
    public List<MessageDTO> pageSendMessage(MessageQO messageQO) {
        return messageMapper.pageSendMessage(messageQO);
    }

    /**
     * 分页查询收信箱
     * @param messageQO
     * @return
     */
    @Override
    public List<MessageDTO> pageReceiverMessage(MessageQO messageQO) {
        return messageMapper.pageReceiverMessage(messageQO);
    }

}
