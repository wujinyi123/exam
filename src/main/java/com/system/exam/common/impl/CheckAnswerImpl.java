package com.system.exam.common.impl;

import com.system.exam.common.ICheckAnswer;
import com.system.exam.domain.dto.exam.AnswerDTO;
import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.dto.exam.StuAnsDTO;
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
    /**
     * 得到考生考试结果
     * @param examResultDTO
     * @param answerQO
     */
    @Override
    public void examResult(ExamResultDTO examResultDTO, AnswerQO answerQO) {
        //考试总分
        int stuScore = 0;

        //核对单选题
        stuScore += checkAnswer(answerQO.getSingleList(),examResultDTO.getSingleAnswerList());
        answerQO.setSingleAnswer(answerQO.getSingleList().toString());

        //核对多选题
        stuScore += checkAnswer(answerQO.getMultipleList(),examResultDTO.getMultipleAnswerList());
        answerQO.setMultipleAnswer(answerQO.getMultipleList().toString());

        //计算总分
        examResultDTO.setScore(stuScore+"");
        answerQO.setScore(stuScore+"");
    }

    /**
     * 得到考生考试结果
     * @param examResultDTO
     * @param stuAnsDTO
     */
    @Override
    public void examResult(ExamResultDTO examResultDTO, StuAnsDTO stuAnsDTO) {
        //考生答案数组
        String[] stuSingle = stuAnsDTO.getSingleAnswer().substring(1,stuAnsDTO.getSingleAnswer().length()-1).split(",");
        String[] stuMultiple = stuAnsDTO.getMultipleAnswer().substring(1,stuAnsDTO.getMultipleAnswer().length()-1).split(",");

        //考试总分
        int stuScore = 0;

        //单选题
        stuScore += checkAnswer(examResultDTO.getSingleAnswerList(),stuSingle);
        //多选题
        stuScore += checkAnswer(examResultDTO.getMultipleAnswerList(),stuMultiple);

        //计算总分
        examResultDTO.setScore(stuScore+"");
    }

    private int checkAnswer(List<AnswerDTO> answerList, String[] stuAns) {
        int len = answerList.size();
        int score = 0;
        for (int i=0; i<len; i++) {
            answerList.get(i).setStuAnswer(stuAns[i].trim());
            if (answerList.get(i).getAnswer().equals(stuAns[i].trim())) {
                score += answerList.get(i).getScore();
            }
        }
        return score;
    }

    /**
     * 核对答案
     * @param stuAns
     * @param answerList
     * @return
     */
    private int checkAnswer(List<String> stuAns, List<AnswerDTO> answerList){
        int len = answerList.size();
        int score = 0;
        String ansStr = "";
        for (int i=0; i<len; i++) {
            ansStr = stuAns.get(i);
            answerList.get(i).setStuAnswer(ansStr);
            if (ansStr.equals(answerList.get(i).getAnswer())) {
                score += answerList.get(i).getScore();
            }
        }
        return score;
    }

}
