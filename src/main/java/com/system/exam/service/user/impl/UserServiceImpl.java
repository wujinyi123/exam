package com.system.exam.service.user.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.common.ImgOperate;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.*;
import com.system.exam.mapper.user.UserMapper;
import com.system.exam.service.user.UserService;
import com.system.exam.common.impl.ImgOperateImpl;
import com.system.exam.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 用户业务层实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Autowired
    private IUserSession userSession;

    @Autowired
    private ImgOperate imgOperate;

    /**
     * 用户登录
     * @param loginQO
     * @return
     */
    @Override
    public LoginDTO login(LoginQO loginQO) {
        //校验验证码
        if (!loginQO.getEnterCode().equals(loginQO.getTrueCode())) {
            return new LoginDTO("no","验证码不正确",loginQO.getUserType());
        }
        //校验用户密码
        loginQO.setPassword(Md5Util.md5(loginQO.getPassword()));
        UserDTO userDTO = userMapper.login(loginQO);
        if (userDTO!=null) {
            /**
             * 存入缓存，并生成token，返回前端
             * 管理员：adminExamSystem，教师：teacherExamSystem，学生：studentExamSystem
             */
            String token = userSession.saveUser(userDTO, loginQO.getUserType()+"ExamSystem");
            return new LoginDTO("ok", token, loginQO.getUserType());
        }
        return new LoginDTO("no","用户名或密码不正确",loginQO.getUserType());
    }

    /**
     * 更新登录
     * @param userType
     * @return
     */
    @Override
    public LoginDTO relogin(String userType) {
        UserDTO userDTO = userSession.getUser(userType + "ExamSystem");
        if (userDTO!=null) {
            /**
             * 存入缓存，并生成token，返回前端
             * 管理员：adminExamSystem，教师：teacherExamSystem，学生：studentExamSystem
             */
            String token = userSession.saveUser(userDTO, userType+"ExamSystem");
            return new LoginDTO("ok", token, userType);
        }
        return new LoginDTO("no","",userType);
    }

    /**
     * 获取用户信息
     * @param userMsgQO
     * @return
     */
    public UserDTO getUserMsg(UserMsgQO userMsgQO) {
        return userSession.getUser(userMsgQO.getUserType()+"ExamSystem");
    }

    /**
     * 获取用户个人资料
     * @return
     */
    @Override
    public UserInfoDTO getUserInfo(UserQO userQO) {
        if (userQO.getNumber()==null || "".equals(userQO.getNumber())) {
            UserDTO userDTO = userSession.getUser(userQO.getUserType()+"ExamSystem");
            userQO.setNumber(userDTO.getNumber());
        }

        //判断不同用户
        switch (userQO.getUserType()) {
            case "admin" : return userMapper.getAdminInfo(userQO);
            case "teacher" : return userMapper.getTeacherInfo(userQO);
            case "student" : return userMapper.getStudentInfo(userQO);
            default : return null;
        }
    }

    /**
     * 修改密码
     * @param udateQO
     * @return
     */
    @Override
    public UdateDTO udatePassword(UdateQO udateQO) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser(udateQO.getUserType()+"ExamSystem");
        //密码加密
        udateQO.setOldPwd(Md5Util.md5(udateQO.getOldPwd()));
        udateQO.setNewPwd(Md5Util.md5(udateQO.getNewPwd()));
        //得到账号/学号
        if (userDTO==null) {
            return new UdateDTO("no", "系统异常");
        } else {
            udateQO.setNumber(userDTO.getNumber());
        }
        //判断密码是否正确
        if (!udateQO.getOldPwd().equals(userDTO.getPassword())) {
            return new UdateDTO("no", "原密码不正确");
        } else if (userMapper.udatePassword(udateQO)>0) {
            return new UdateDTO("ok", "修改成功，请重新登录");
        } else {
            return new UdateDTO("no", "系统异常");
        }
    }

    /**
     * 修改电话和邮箱
     * @param udateQO
     * @return
     */
    @Override
    public UdateDTO udateTelEmail(UdateQO udateQO) {
        UserDTO userDTO = userSession.getUser(udateQO.getUserType()+"ExamSystem");
        udateQO.setNumber(userDTO.getNumber());
        if (udateQO.getTel().equals(userDTO.getTel()) && udateQO.getEmail().equals(userDTO.getEmail())) {
            return new UdateDTO("no", "未发现改动");
        }
        //修改成功后，用存回redis
        if (userMapper.udateTelEmail(udateQO)>0) {
            userDTO.setTel(udateQO.getTel());
            userDTO.setEmail(udateQO.getEmail());
            userSession.updateUser(udateQO.getUserType()+"ExamSystem", userDTO);
            return new UdateDTO("ok", "修改成功");
        } else {
            return new UdateDTO("no", "系统异常");
        }
    }

    /**
     * 上传头像
     * @param file
     * @param type
     * @return
     */
    @Override
    public ImgUploadDTO imgUpload(MultipartFile file, String type) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser(type+"ExamSystem");
        ImgUploadDTO imgUploadDTO = imgOperate.imgUpload(file,"/"+type+"/"+userDTO.getNumber());

        if (!"0".equals(imgUploadDTO.getCode())) {
            UdateQO udateQO = new UdateQO();
            udateQO.setUserType(type);
            udateQO.setNumber(userDTO.getNumber());
            udateQO.setImg(imgUploadDTO.getImgUrl());
            userMapper.udateImg(udateQO);
        }
        return imgUploadDTO;
    }

    /**
     * 更新用户信息
     * @param updateInfoQO
     * @return
     */
    @Override
    public String updateInfo(UpdateInfoQO updateInfoQO) {
        String result = "";
        switch (updateInfoQO.getUserType()) {
            case "admin":{
                result = userMapper.updateAdmin(updateInfoQO)+"";
                break;
            }
            case "teacher":{
                result = userMapper.updateTeacher(updateInfoQO)+"";
                break;
            }
            case "student":{
                result = userMapper.updateStudent(updateInfoQO)+"";
                break;
            }
            default:result = "0";
        }
        return result;
    }

    /**
     * 重置密码
     * @param resetPasswordQO
     * @return
     */
    @Override
    public String resetPassword(ResetPasswordQO resetPasswordQO) {
        return userMapper.resetPassword(resetPasswordQO)+"";
    }

    /**
     * 删除用户
     * @param deleteUserQO
     * @return
     */
    @Override
    public String deleteUser(DeleteUserQO deleteUserQO) {
        return userMapper.deleteUser(deleteUserQO)+"";
    }

}
