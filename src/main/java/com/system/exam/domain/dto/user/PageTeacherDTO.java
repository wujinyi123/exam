package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页查询教师DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageTeacherDTO {
    /**
     * 教师号
     */
    private String number;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 学院
     */
    private String college;

    /**
     * 学院代码
     */
    private String collegeCode;

    /**
     * 电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;
}
