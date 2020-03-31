package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 最新成绩
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewStuScoreDTO {
    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 姓名
     */
    private String stuName;

    /**
     * 学院
     */
    private String college;

    /**
     * 班级
     */
    private String clazz;

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
    private String examScore;

    /**
     * 成绩
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
