package com.system.exam.domain.qo.userMessage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 查询用户信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQO {
    /**
     * 用户类型  admin：管理员，teacher：教师，student：学生
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;
}
