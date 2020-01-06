package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生个人资料DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentInfoDTO {
    /**
     * 学院
     */
    private String college;

    /**
     * 专业
     */
    private String major;

    /**
     * 班级
     */
    private String clazz;

    /**
     * 学号
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
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String img;

}
