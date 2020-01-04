package com.system.exam.service.user.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.user.LoginDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import com.system.exam.mapper.user.UserMapper;
import com.system.exam.service.user.UserService;
import com.system.exam.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            String token = userSession.saveUser(userDTO, loginQO+"ExamSystem");
            return new LoginDTO("ok", token, loginQO.getUserType());
        }
        return new LoginDTO("no","用户名或密码不正确",loginQO.getUserType());
    }

}
