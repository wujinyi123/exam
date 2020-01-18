package com.system.exam.mapper.exam;

import com.system.exam.domain.dto.exam.EnterExamDTO;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.dto.exam.QuestionDTO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.domain.qo.exam.NewExamQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户mapper
 */
@Mapper
public interface ExamMapper {
    /**
     * 查询考试码
     * @param examQO
     * @return
     */
    ExamDTO getExamByCode(ExamQO examQO);

    /**
     * 获取未参加且未超过截止时间的考试
     * @param newExamQO
     * @return
     */
    List<ExamDTO> pageNewExam(NewExamQO newExamQO);

    /**
     * 近期（5条）考试成绩 （学生）
     * @param number
     * @return
     */
    List<ExamDTO> listNewScore(String number);

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    EnterExamDTO enterExam(ExamQO examQO);

    /**
     * 根据题目类型，得到题目集合
     * @param examQO
     * @return
     */
    List<QuestionDTO> listQuestion(ExamQO examQO);

}
