package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题目DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    /**
     * 题目
     */
    private String question;

    /**
     * 答案
     */
    private String answer;

    /**
     * 分数
     */
    private String score;

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
     * 答案解析
     */
    private String analysis;

    /**
     * 题目图片地址
     */
    private String imgQuestion;

    /**
     * A选项图片地址
     */
    private String imgA;

    /**
     * B选项图片地址
     */
    private String imgB;

    /**
     * C选项图片地址
     */
    private String imgC;

    /**
     * D选项图片地址
     */
    private String imgD;

    /**
     * 答案解析图片地址
     */
    private String imgAnalysis;

}
