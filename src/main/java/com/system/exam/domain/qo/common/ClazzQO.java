package com.system.exam.domain.qo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询班级QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClazzQO {
    /**
     * 学院代码
     */
    private String collegeCode;

    /**
     * 年级
     */
    private String year;
}
