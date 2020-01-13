package com.system.exam.mapper.exam;

import com.system.exam.domain.dto.exam.ExamDTO;
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

}
