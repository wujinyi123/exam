package com.system.exam.mapper.user;

import com.system.exam.domain.dto.user.UserInfoDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.*;
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

    /**
     * 更新用户信息
     * @param updateInfoQO
     * @return
     */
    int updateAdmin(UpdateInfoQO updateInfoQO);
    int updateTeacher(UpdateInfoQO updateInfoQO);
    int updateStudent(UpdateInfoQO updateInfoQO);

    /**
     * 重置密码
     * @param resetPasswordQO
     * @return
     */
    int resetPassword(ResetPasswordQO resetPasswordQO);
}
