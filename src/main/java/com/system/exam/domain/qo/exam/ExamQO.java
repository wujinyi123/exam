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
     * 考试码
     */
    @NotBlank(message = "考试码不能为空")
    private String examCode;

    /**
     * 账号/学号
     */
    private String number;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 学生成绩
     */
    private String stuScore;

    public ExamQO(@NotBlank(message = "考试码不能为空") String examCode) {
        this.examCode = examCode;
    }
}
