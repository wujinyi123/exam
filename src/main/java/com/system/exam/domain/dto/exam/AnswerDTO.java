package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目答案DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDTO {
    /**
     * 考生答案
     */
    private String stuAnswer;

    /**
     * 正确答案
     */
    private String answer;

    /**
     * 分值
     */
    private String score;

    /**
     * 答案解析
     */
    private String answerAnalysis;

}
