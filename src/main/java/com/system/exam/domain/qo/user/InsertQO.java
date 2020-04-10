package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 导入用户信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertQO {
    /**
     * 类型：教师/学生/班级
     */
    private String type;

    /**
     * 数据组
     */
    /*private List<String> dataList;*/

    private String flag;

    private String collegeCode;
    private String year;
    private String clazz;
    private String major;

    private String number;
    private String password;
    private String name;
    private String sex;
    private String tel;
    private String email;
}
