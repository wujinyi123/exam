package com.system.exam.mapper.user;

import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.user.LoginQO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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

}
