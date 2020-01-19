package com.system.exam.service.exam;

import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.exam.EnterExamDTO;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.qo.exam.AnswerQO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.domain.qo.exam.NewExamQO;

import java.util.List;

/**
 * 考试业务层接口类
 */
public interface ExamService {
    /**
     * 查询考试码（学生）
     * @param examQO
     * @return
     */
    ExamDTO getExamByCode(ExamQO examQO);

    /**
     * 获取未参加且未超过截止时间的考试（学生）
     * @param newExamQO
     * @return
     */
    List<ExamDTO> pageNewExam(NewExamQO newExamQO);

    /**
     * 近期（5条）考试成绩 （学生）
     * @return
     */
    List<ExamDTO> listNewScore();

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    EnterExamDTO enterExam(ExamQO examQO);

    /**
     * 提交答案
     * @param answerQO
     * @return
     */
    ExamResultDTO submitAnswer(AnswerQO answerQO);

}
