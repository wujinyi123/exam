package com.system.exam.service.userMessage.impl;

import com.system.exam.domain.dto.userMessage.LoginMsgDTO;
import com.system.exam.domain.dto.userMessage.UserDTO;
import com.system.exam.domain.qo.userMessage.LoginMsgQO;
import com.system.exam.domain.qo.userMessage.UserQO;
import com.system.exam.mapper.userMessage.UserMapper;
import com.system.exam.service.userMessage.UserService;
import com.system.exam.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     * @param loginMsgQO
     * @return
     */
    @Override
    public LoginMsgDTO login(LoginMsgQO loginMsgQO, HttpServletRequest request) {
        //校验验证码
        if (!loginMsgQO.getTrueCode().equals(loginMsgQO.getEnterCode())) {
            return new LoginMsgDTO("no","登录失败！验证码错误");
        }
        //校验密码
        loginMsgQO.setPassword(Md5Util.md5(loginMsgQO.getPassword()));
        UserDTO userDTO = userMapper.login(loginMsgQO);
        if (userDTO!=null) {
            request.getSession().setAttribute(loginMsgQO.getUserType(),userDTO);
            return new LoginMsgDTO("ok","login");
        } else {
            return new LoginMsgDTO("no","登录失败！用户名或密码错误");
        }
    }

    /**
     * 得到用户信息
     * @param userQO
     * @return
     */
    @Override
    public UserDTO getUser(UserQO userQO, HttpServletRequest request) {
        return (UserDTO) request.getSession().getAttribute(userQO.getUserType());
    }
}
