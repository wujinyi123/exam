package com.system.exam.service.exam;

import com.system.exam.domain.dto.exam.ExamDTO;
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

}
