package com.system.exam.domain.qo.user;

import com.system.exam.common.PageQO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 分页查询班级QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageClazzQO extends PageQO {
    /**
     * 学院
     */
    private String collegeCode;

    /**
     * 年级
     */
    private String year;
}
