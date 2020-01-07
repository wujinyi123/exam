package com.system.exam.service.exam;

import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.qo.exam.ExamQO;

/**
 * 考试业务层接口类
 */
public interface ExamService {
    /**
     * 查询考试码
     * @param examQO
     * @return
     */
    ExamDTO getExamByCode(ExamQO examQO);

}
