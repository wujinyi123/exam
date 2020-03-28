package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClazzGradeQO {
    /**
     * 考试码
     */
    @NotBlank(message = "考试码不能为空")
    private String examCode;

    /**
     * 班级号
     */
    @NotBlank(message = "班级号不能为空")
    private String clazzNumber;
}
