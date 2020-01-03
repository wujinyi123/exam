package com.system.exam.domain.dto.userMessage;

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
public class LoginMsgDTO {
    /**
     * 状态信息  ok：成功，no：失败
     */
    private String state;

    /**
     * 登录失败的信息
     */
    private String erro;

}
