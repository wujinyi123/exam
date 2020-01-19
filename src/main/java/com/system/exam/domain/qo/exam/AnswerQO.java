package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 提交答案QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerQO {
    /**
     * 考试码
     */
    @NotBlank(message = "考试码不能为空")
    private String examCode;

    /**
     * 学号
     */
    @NotBlank(message = "学号不能为空")
    private String stuNumber;

    /**
     * 单选答案，用英文逗号隔开
     */
    private String singleAnswer;

    /**
     * 多选答案，用英文逗号隔开
     */
    private String multipleAnswer;

    /**
     * 成绩
     */
    private String score;

    /**
     * 用时
     */
    @NotBlank(message = "用时不能为空")
    private String useTime;

    /**
     * 提交时间
     */
    @NotBlank(message = "提交时间不能为空")
    private String submitTime;

}
