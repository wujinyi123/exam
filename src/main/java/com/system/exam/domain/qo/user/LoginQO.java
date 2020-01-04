package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 登录信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginQO {
    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String number;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 用户类型  admin：管理员，teacher：教师，student：学生
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;

    /**
     * 输入的验证码
     */
    @NotBlank(message = "输入的验证码不能为空")
    private String enterCode;

    /**
     * 正确的验证码
     */
    @NotBlank(message = "正确的验证码不能为空")
    private String trueCode;

}
