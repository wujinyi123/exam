package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新建考试DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewBuiltExamDTO {
    /**
     * 添加是否成功
     */
    private String state;

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
     * 截止时间
     */
    private String expTime;
}
