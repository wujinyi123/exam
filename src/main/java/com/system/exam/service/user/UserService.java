package com.system.exam.service.user;

import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UserQO;

/**
 * 用户业务层接口类
 */
public interface UserService {
    /**
     * 用户登录
     * @param loginQO
     * @return
     */
    LoginDTO login(LoginQO loginQO);

    /**
     * 获取用户信息
     * @param userQO
     * @return
     */
    UserDTO getUserMsg(UserQO userQO);

}
