package com.system.exam.controller.user;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UserQO;
import com.system.exam.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户
 */
@Validated
@RestController
@RequestMapping("/back/exam/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param loginQO
     * @return
     */
    @PostMapping("/login")
    public ResponseData<LoginDTO> login(@RequestBody @Valid LoginQO loginQO) {
        return ResponseDataUtil.buildSuccess(userService.login(loginQO));
    }

    /**
     * 获取用户信息
     * @param userQO
     * @return
     */
    @PostMapping("/getUserMsg")
    public ResponseData<UserDTO> getUserMsg(@RequestBody @Valid UserQO userQO) {
        return ResponseDataUtil.buildSuccess(userService.getUserMsg(userQO));
    }

}
