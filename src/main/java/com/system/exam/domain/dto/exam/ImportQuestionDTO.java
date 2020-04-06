package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 导入题目DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportQuestionDTO {
    /**
     * 题目
     */
    private String question;

    /**
     * A选项
     */
    private String optionA;

    /**
     * B选项
     */
    private String optionB;

    /**
     * C选项
     */
    private String optionC;

    /**
     * D选项
     */
    private String optionD;

    /**
     * 答案
     */
    private String answer;

    /**
     * 答案解析
     */
    private String analysis;
}
