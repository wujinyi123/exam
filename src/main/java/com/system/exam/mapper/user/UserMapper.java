package com.system.exam.mapper.user;

import com.system.exam.domain.dto.user.UserInfoDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
import com.system.exam.domain.qo.user.UserQO;
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
     * 获取管理员个人资料
     * @param userQO
     * @return
     */
    UserInfoDTO getAdminInfo(UserQO userQO);

    /**
     * 获取教师个人资料
     * @param userQO
     * @return
     */
    UserInfoDTO getTeacherInfo(UserQO userQO);

    /**
     * 获取学生个人资料
     * @param userQO
     * @return
     */
    UserInfoDTO getStudentInfo(UserQO userQO);

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

    /**
     * 上传头像
     * @param udateQO
     * @return
     */
    int udateImg(UdateQO udateQO);
}
