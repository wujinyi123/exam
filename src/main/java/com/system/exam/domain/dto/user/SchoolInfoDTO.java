package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全校情况DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SchoolInfoDTO {
    /**
     * 学院数
     */
    private String colleges;

    /**
     * 班级数
     */
    private String clazzs;

    /**
     * 学生数
     */
    /*private String students;*/

    /**
     * 男生人数
     */
    private String mstudents;

    /**
     * 女生人数
     */
    private String gstudents;

    /**
     * 教师数
     */
    /*private String teachers;*/

    /**
     * 男教师数
     */
    private String mteachers;

    /**
     * 女教师数
     */
    private String gteachers;
}
