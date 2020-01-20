package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考试答案DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StuAnsDTO {
    /**
     * 成绩
     */
    private String score;

    /**
     * 单选答案，用英文逗号隔开
     */
    private String singleAnswer;

    /**
     * 多选答案，用英文逗号隔开
     */
    private String multipleAnswer;

}
