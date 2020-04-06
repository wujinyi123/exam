package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询班级DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageClazzDTO {
    /**
     * 学院
     */
    private String college;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 专业
     */
    private String major;

    /**
     * 年级
     */
    private String year;
}
