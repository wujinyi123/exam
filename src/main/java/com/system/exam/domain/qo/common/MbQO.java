package com.system.exam.domain.qo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MbQO {
    /**
     * 账号/学号
     */
    private String number;

}
