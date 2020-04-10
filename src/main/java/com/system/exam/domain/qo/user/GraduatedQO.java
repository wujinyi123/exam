package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 毕业QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GraduatedQO {
    /**
     * 学院代码
     */
    private String collegeCode;

    /**
     * 年级
     */
    private String year;

    /**
     * 班级
     */
    private String clazz;
}
