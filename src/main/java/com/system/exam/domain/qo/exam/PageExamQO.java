package com.system.exam.domain.qo.exam;

import com.system.exam.common.PageQO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 教师分页查询考试QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageExamQO extends PageQO {
    /**
     * 教师账号
     */
    private String teacherNumber;

    /**
     * 模糊条件
     */
    private String examTerm;
}
