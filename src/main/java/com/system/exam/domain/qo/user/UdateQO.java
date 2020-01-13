package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 更新信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UdateQO {
    /**
     * 用户类型  admin：管理员，teacher：教师，student：学生
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;

    /**
     * 账号/学号
     */
    private String number;

    /**
     * 原密码
     */
    private String oldPwd;

    /**
     * 新密码
     */
    private String newPwd;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

}
