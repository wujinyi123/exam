package com.system.exam.common.impl;

import com.system.exam.common.ICheckAnswer;
import com.system.exam.domain.dto.exam.AnswerDTO;
import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.qo.exam.AnswerQO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 答案工具
 */
@Component
public class CheckAnswerImpl implements ICheckAnswer {
    @Autowired
    private HttpServletRequest request;

    /**
     * 得到考生考试结果
     * @param examResultDTO
     * @param answerQO
     */
    @Override
    public void examResult(ExamResultDTO examResultDTO, AnswerQO answerQO) {
        //考试总分
        int stuScore = 0;
        List<String> stuAnswers = null;

        //核对单选题
        stuAnswers = new ArrayList<>();
        stuScore += checkAnswer(0,stuAnswers,examResultDTO.getSingleAnswerList());
        answerQO.setSingleAnswer(stuAnswers.toString());

        //核对多选题
        stuAnswers = new ArrayList<>();
        stuScore += checkAnswer(1,stuAnswers,examResultDTO.getMultipleAnswerList());
        answerQO.setMultipleAnswer(stuAnswers.toString());

        //计算总分
        examResultDTO.setScore(stuScore+"");
        answerQO.setScore(stuScore+"");
    }

    /**
     * 核对答案
     * @param type
     * @param stuAnswers
     * @param answerList
     * @return
     */
    private int checkAnswer(int type, List<String> stuAnswers, List<AnswerDTO> answerList){
        String[] typeArray = {"single_answer_","multiple_answer_"};
        int len = answerList.size();
        int score = 0;
        String stuAns = "";
        for (int i=0; i<len; i++) {
            stuAns = getStuAns(type,typeArray[type]+i);
            if (stuAns == null) {
                stuAns = "";
            }
            stuAnswers.add(stuAns);
            answerList.get(i).setStuAnswer(stuAns);
            if (stuAns.equals(answerList.get(i).getAnswer())) {
                score += answerList.get(i).getScore();
            }
        }
        return score;
    }

    /**
     * 根据题号获取考生当题答案
     * @param type
     * @param key
     * @return
     */
    private String getStuAns(int type,String key) {
        String strAns = "";
        if (type==1) {
            String[] arr = request.getParameterValues(key);
            if (arr == null) {
                arr = new String[0];
            }
            Arrays.sort(arr);
            for (String str:arr) {
                strAns += str;
            }
        } else {
            strAns = request.getParameter(key);
            if (strAns == null) {
                strAns = "";
            }
        }
        return strAns;
    }

}
