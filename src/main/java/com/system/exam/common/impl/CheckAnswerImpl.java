package com.system.exam.common.impl;

import com.system.exam.common.ICheckAnswer;
import com.system.exam.domain.qo.exam.AnswerQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 答案工具
 */
@Component
public class CheckAnswerImpl implements ICheckAnswer {
    @Autowired
    private HttpServletRequest request;

    /**
     * 得到考生答题情况
     * @param answerQO
     */
    @Override
    public void getStuAnswer(AnswerQO answerQO) {

    }

}
