package com.system.exam.mapper.userMessage;

import com.system.exam.domain.dto.userMessage.UserDTO;
import com.system.exam.domain.qo.userMessage.LoginMsgQO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * 用户登录
     * @param loginMsgQO
     * @return
     */
    UserDTO login(LoginMsgQO loginMsgQO);

}
