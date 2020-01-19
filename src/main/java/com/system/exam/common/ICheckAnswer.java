package com.system.exam.common;

import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.qo.exam.AnswerQO;

/**
 * 答案工具
 */
public interface ICheckAnswer {
    /**
     * 得到考生答题情况
     * @param answerQO
     */
    void getStuAnswer(AnswerQO answerQO);

}
