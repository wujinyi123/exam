package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新建题目QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewBuiltQuestionQO {
    /**
     * 考试码
     */
    private String examCode;

    /**
     * 题型
     */
    private String type;

    /**
     * 分值
     */
    private String score;

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

    /**
     * 题目img
     */
    private String imgQuestion;

    /**
     * A选项img
     */
    private String imgA;

    /**
     * B选项img
     */
    private String imgB;

    /**
     * C选项img
     */
    private String imgC;

    /**
     * D选项img
     */
    private String imgD;

    /**
     * 答案img
     */
    private String imgAnswer;

    /**
     * 答案解析img
     */
    private String imgAnalysis;

}
