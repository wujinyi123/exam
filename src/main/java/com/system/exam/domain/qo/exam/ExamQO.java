package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 考试信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamQO {
    /**
     * 用户类型
     */
    @NotBlank(message = "用户类型不能为空")
    private String userType;

    /**
     * 考试码
     */
    @NotBlank(message = "考试码不能为空")
    private String examCode;

    /**
     * 账号/学号
     */
    private String number;

}
