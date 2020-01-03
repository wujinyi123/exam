package com.system.exam.controller.userMessage;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.userMessage.LoginMsgDTO;
import com.system.exam.domain.dto.userMessage.UserDTO;
import com.system.exam.domain.qo.userMessage.LoginMsgQO;
import com.system.exam.domain.qo.userMessage.UserQO;
import com.system.exam.service.userMessage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 用户
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginMsgQO
     * @return
     */
    @PostMapping("/login")
    public ResponseData<LoginMsgDTO> login(@RequestBody @Valid LoginMsgQO loginMsgQO, HttpServletRequest request) {
        return ResponseDataUtil.buildSuccess(userService.login(loginMsgQO,request));
    }

    /**
     * 得到用户信息
     * @param userQO
     * @return
     */
    @PostMapping("/getUser")
    public ResponseData<UserDTO> getUser(@RequestBody @Valid UserQO userQO, HttpServletRequest request) {
        return ResponseDataUtil.buildSuccess(userService.getUser(userQO,request));
    }

}
