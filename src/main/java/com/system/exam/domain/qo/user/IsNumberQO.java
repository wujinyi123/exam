package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询账户/班级号是否存在QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IsNumberQO {
    /**
     * 类型
     */
    private String type;

    /**
     * 账户
     */
    private String number;
}
