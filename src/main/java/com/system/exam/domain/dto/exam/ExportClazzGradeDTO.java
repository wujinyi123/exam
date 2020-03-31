package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExportClazzGradeDTO {
    /**
     * 考试码
     */
    private String examCode;

    /**
     * 小测名称
     */
    private String examName;

    /**
     * 总分
     */
    private String score;

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
     * 学生号
     */
    private String stuNumber;

    /**
     * 学生姓名
     */
    private String stuName;

    /**
     * 学生成绩
     */
    private String stuScore;

    /**
     * 用时
     */
    private String useTime;

    /**
     * 提交时间
     */
    private String submitTime;
}
