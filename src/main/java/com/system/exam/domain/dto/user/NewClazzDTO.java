package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新一届年级DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewClazzDTO {
    /**
     * 学院代码
     */
    private String collegeCode;

    /**
     * 年级
     */
    private String year;

    /**
     * 班级号
     */
    private String clazz;

    /**
     * 专业
     */
    private String major;
}
