package com.system.exam.service.user;

import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.UserInfoDTO;
import com.system.exam.domain.dto.user.UdateDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
import com.system.exam.domain.qo.user.UserMsgQO;
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
     * @param userMsgQO
     * @return
     */
    UserDTO getUserMsg(UserMsgQO userMsgQO);

    /**
     * 获取当前用户个人资料
     * @return
     */
    UserInfoDTO getUserInfo(UserQO userQO);

    /**
     * 修改密码
     * @param udateQO
     * @return
     */
    UdateDTO udatePassword(UdateQO udateQO);

    /**
     * 修改电话和邮箱
     * @param udateQO
     * @return
     */
    UdateDTO udateTelEmail(UdateQO udateQO);

}
