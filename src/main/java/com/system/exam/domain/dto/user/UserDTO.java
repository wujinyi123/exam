package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户信息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    /**
     * 用户id
     */
    private String id;

    /**
     * 用户账号
     */
    private String number;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户性别
     */
    private String sex;

    /**
     * 用户电话
     */
    private String tel;

    /**
     * 用户email
     */
    private String email;

    /**
     * 用户头像
     */
    private String img_path;

    /**
     * 用户状态
     */
    private String state;

}
