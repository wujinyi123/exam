package com.system.exam.domain.qo.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 查询用户消息条数QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCountQO {
    /**
     * 用户类型  admin：管理员，teacher：教师，student：学生
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;

    /**
     * 账号
     */
    private String number;
}
