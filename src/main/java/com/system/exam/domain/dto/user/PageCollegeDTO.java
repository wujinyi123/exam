package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageCollegeDTO {
    /**
     * 学院代码
     */
    private String code;

    /**
     * 学院名称
     */
    private String name;

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
