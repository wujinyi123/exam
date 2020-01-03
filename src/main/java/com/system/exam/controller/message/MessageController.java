package com.system.exam.controller.message;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.message.MessageCountDTO;
import com.system.exam.domain.dto.message.MessageDTO;
import com.system.exam.domain.qo.message.MessageCountQO;
import com.system.exam.domain.qo.message.MessageQO;
import com.system.exam.service.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 消息
 */
@Validated
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 用户消息条数
     * @param messageCountQO
     * @return
     */
    @PostMapping("/getMessageCount")
    public ResponseData<MessageCountDTO> getMessageCount(@RequestBody @Valid MessageCountQO messageCountQO) {
        return ResponseDataUtil.buildSuccess(messageService.getMessageCount(messageCountQO));
    }

    /**
     * 分页查询新消息
     * @param messageQO
     * @return
     */
    @RequestMapping("/pageNewMessage")
    public ResponseData<List<MessageDTO>> pageNewMessage(@Valid MessageQO messageQO) {
        return ResponseDataUtil.buildSuccess(messageService.pageNewMessage(messageQO),messageQO);
    }

    /**
     * 分页查询已发信息
     * @param messageQO
     * @return
     */
    @RequestMapping("/pageSendMessage")
    public ResponseData<List<MessageDTO>> pageSendMessage(@Valid MessageQO messageQO) {
        return ResponseDataUtil.buildSuccess(messageService.pageSendMessage(messageQO),messageQO);
    }

    /**
     * 分页查询收信箱
     * @param messageQO
     * @return
     */
    @RequestMapping("/pageReceiverMessage")
    public ResponseData<List<MessageDTO>> pageReceiverMessage(@Valid MessageQO messageQO) {
        return ResponseDataUtil.buildSuccess(messageService.pageReceiverMessage(messageQO),messageQO);
    }

}
