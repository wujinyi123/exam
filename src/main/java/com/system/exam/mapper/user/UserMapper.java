package com.system.exam.mapper.user;

import com.system.exam.domain.dto.user.UserInfoDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户mapper
 */
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     * @param loginQO
     * @return
     */
    UserDTO login(LoginQO loginQO);

    /**
     * 获取当前用户（管理员）个人资料
     * @param number
     * @return
     */
    UserInfoDTO getAdminInfo(String number);

    /**
     * 获取当前用户（教师）个人资料
     * @param number
     * @return
     */
    UserInfoDTO getTeacherInfo(String number);

    /**
     * 获取当前用户（学生）个人资料
     * @param number
     * @return
     */
    UserInfoDTO getStudentInfo(String number);

    /**
     * 修改密码
     * @param udateQO
     * @return
     */
    int udatePassword(UdateQO udateQO);

    /**
     * 修改电话和邮箱
     * @param udateQO
     * @return
     */
    int udateTelEmail(UdateQO udateQO);

}
