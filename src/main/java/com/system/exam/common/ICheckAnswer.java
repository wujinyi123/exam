package com.system.exam.common;

import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.dto.exam.StuAnsDTO;
import com.system.exam.domain.qo.exam.AnswerQO;

/**
 * 答案工具
 */
public interface ICheckAnswer {
    /**
     * 得到考生考试结果
     * @param examResultDTO
     * @param answerQO
     */
    void examResult(ExamResultDTO examResultDTO,AnswerQO answerQO);

    /**
     * 得到考生考试结果
     * @param examResultDTO
     * @param stuAnsDTO
     */
    void examResult(ExamResultDTO examResultDTO, StuAnsDTO stuAnsDTO);

}
