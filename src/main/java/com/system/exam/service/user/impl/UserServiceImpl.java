package com.system.exam.service.user.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.UserInfoDTO;
import com.system.exam.domain.dto.user.UdateDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.domain.qo.user.UdateQO;
import com.system.exam.domain.qo.user.UserQO;
import com.system.exam.mapper.user.UserMapper;
import com.system.exam.service.user.UserService;
import com.system.exam.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * 获取用户信息
     * @param userQO
     * @return
     */
    public UserDTO getUserMsg(UserQO userQO) {
        if (StringUtils.isEmpty(userQO.getToken())) {
            return userSession.getUser(userQO.getUserType()+"ExamSystem");
        } else {
            return userSession.getUserByKeyToken(userQO.getUserType()+"ExamSystem", userQO.getToken());
        }
    }

    /**
     * 获取当前用户个人资料
     * @return
     */
    @Override
    public UserInfoDTO getUserInfo(UserQO userQO) {
        UserDTO userDTO = userSession.getUser(userQO.getUserType()+"ExamSystem");
        if (userDTO==null) {
            return null;
        } else {
            //判断不同客户端
            switch (userQO.getUserType()) {
                case "admin" : return userMapper.getAdminInfo(userDTO.getNumber());
                case "teacher" : return userMapper.getTeacherInfo(userDTO.getNumber());
                case "student" : return userMapper.getStudentInfo(userDTO.getNumber());
                default : return null;
            }
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

}