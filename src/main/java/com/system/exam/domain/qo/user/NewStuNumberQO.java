package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 新一届学生学号QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewStuNumberQO {
    /**
     * 学院代码
     */
    @NotBlank(message = "学院代码不能为空")
    private String collegeCode;

    /**
     * 学院名称
     */
    @NotBlank(message = "学院名称不能为空")
    private String collegeName;

    /**
     * 男生人数
     */
    @NotBlank(message = "男生人数不能为空")
    private String boy;

    /**
     * 女生人数
     */
    @NotBlank(message = "女生人数不能为空")
    private String girl;
}
