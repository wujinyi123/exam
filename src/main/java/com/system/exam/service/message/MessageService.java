package com.system.exam.service.message;

import com.system.exam.domain.dto.message.MessageCountDTO;
import com.system.exam.domain.dto.message.MessageDTO;
import com.system.exam.domain.qo.message.MessageCountQO;
import com.system.exam.domain.qo.message.MessageQO;

import java.util.List;

/**
 * 消息
 */
public interface MessageService {
    /**
     * 用户消息条数
     * @param messageCountQO
     * @return
     */
    MessageCountDTO getMessageCount(MessageCountQO messageCountQO);

    /**
     * 分页查询新消息
     * @param messageQO
     * @return
     */
    List<MessageDTO> pageNewMessage(MessageQO messageQO);

    /**
     * 分页查询已发消息
     * @param messageQO
     * @return
     */
    List<MessageDTO> pageSendMessage(MessageQO messageQO);

    /**
     * 分页查询收信箱
     * @param messageQO
     * @return
     */
    List<MessageDTO> pageReceiverMessage(MessageQO messageQO);

}
