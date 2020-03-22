package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 教师分页查询考试DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageExamDTO {
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
}
