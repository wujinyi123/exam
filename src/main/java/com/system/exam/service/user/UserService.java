package com.system.exam.service.user;

import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.StudentInfoDTO;
import com.system.exam.domain.dto.user.UdateDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
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

    /**
     * 获取当前用户（学生）个人资料
     * @return
     */
    StudentInfoDTO getStudentInfo();

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
