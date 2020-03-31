package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考试信息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamDTO {
    /**
     * 是否存在
     */
    private String isFlag;

    /**
     * 查询信息
     */
    private String msg;

    /**
     * 考试码
     */
    private String examCode;

    /**
     * 考试名称
     */
    private String examName;

    /**
     * 总分
     */
    private String score;

    /**
     * 出题老师
     */
    private String teacherName;

    /**
     * 考试时间
     */
    private String time;

    /**
     * 出题时间
     */
    private String pdDate;

    /**
     * 截止时间
     */
    private String expDate;

    /**
     * 备注
     * （学生）0：未参加，1：已参加，2：错过考试
     */
    private String remark;

    /**
     * 我的成绩
     */
    private String myScore;

    /**
     * 提交时间
     */
    private String submitTime;

}
