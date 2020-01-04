package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录信息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginDTO {
    /**
     * 状态信息  ok：成功，no：失败
     */
    private String state;

    /**
     * 登录信息
     */
    private String msg;

    /**
     * 用户类型
     */
    private String userType;

}
