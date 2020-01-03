package com.system.exam.service.userMessage;

import com.system.exam.domain.dto.userMessage.LoginMsgDTO;
import com.system.exam.domain.dto.userMessage.UserDTO;
import com.system.exam.domain.qo.userMessage.LoginMsgQO;
import com.system.exam.domain.qo.userMessage.UserQO;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 */
public interface UserService {
    /**
     * 用户登录
     * @param loginMsgQO
     * @return
     */
    LoginMsgDTO login(LoginMsgQO loginMsgQO, HttpServletRequest request);

    /**
     * 得到用户信息
     * @param userQO
     * @return
     */
    UserDTO getUser(UserQO userQO, HttpServletRequest request);
}
