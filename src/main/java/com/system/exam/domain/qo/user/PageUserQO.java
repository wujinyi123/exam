package com.system.exam.domain.qo.user;

import com.system.exam.common.PageQO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 分页查询教师/学生QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageUserQO extends PageQO {
    /**
     * 学院代码
     */
    @NotBlank(message = "学院代码不能为空")
    private String collegeCode;

    /**
     * 年级
     */
    private String year;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 性别
     */
    @NotBlank(message = "性别不能为空")
    private String sex;

    /**
     * 模糊条件
     */
    private String term;
}
