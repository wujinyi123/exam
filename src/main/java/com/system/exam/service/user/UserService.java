package com.system.exam.service.user;

import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.*;
import org.springframework.web.multipart.MultipartFile;

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
     * 更新登录
     * @param userType
     * @return
     */
    LoginDTO relogin(String userType);

    /**
     * 获取用户信息
     * @param userMsgQO
     * @return
     */
    UserDTO getUserMsg(UserMsgQO userMsgQO);

    /**
     * 获取用户个人资料
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

    /**
     * 上传头像
     * @param file
     * @param type
     * @return
     */
    ImgUploadDTO imgUpload(MultipartFile file, String type);

    /**
     * 更新用户信息
     * @param updateInfoQO
     * @return
     */
    String updateInfo(UpdateInfoQO updateInfoQO);

    /**
     * 重置密码
     * @param resetPasswordQO
     * @return
     */
    String resetPassword(ResetPasswordQO resetPasswordQO);
}
