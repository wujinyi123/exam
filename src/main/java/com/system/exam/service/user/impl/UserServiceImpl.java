package com.system.exam.service.user.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.StudentInfoDTO;
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
     * 获取当前用户（学生）个人资料
     * @return
     */
    @Override
    public StudentInfoDTO getStudentInfo() {
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        if (userDTO==null) {
            return null;
        } else {
            return userMapper.getStudentInfo(userDTO.getNumber());
        }
    }

    /**
     * 修改密码
     * @param udateQO
     * @return
     */
    @Override
    public UdateDTO udatePassword(UdateQO udateQO) {
        UserDTO userDTO = userSession.getUser(udateQO.getUserType()+"ExamSystem");
        udateQO.setOldPwd(Md5Util.md5(udateQO.getOldPwd()));
        udateQO.setNewPwd(Md5Util.md5(udateQO.getNewPwd()));
        udateQO.setNumber(userDTO.getNumber());
        if (userDTO==null) {
            return new UdateDTO("no", "系统异常");
        } else if (!udateQO.getOldPwd().equals(userDTO.getPassword())) {
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
        if (userMapper.udateTelEmail(udateQO)>0) {
            return new UdateDTO("ok", "修改成功");
        } else {
            return new UdateDTO("no", "系统异常");
        }
    }


}
