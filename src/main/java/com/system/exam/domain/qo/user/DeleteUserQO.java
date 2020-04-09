package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 删除用户QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteUserQO {
    /**
     * 用户类型  admin：管理员，teacher：教师，student：学生
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;

    /**
     * 账号
     */
    @NotBlank(message = "账号不能为空")
    private String number;
}
