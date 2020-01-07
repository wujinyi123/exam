package com.system.exam.mapper.exam;

import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.qo.exam.ExamQO;
import org.apache.ibatis.annotations.Mapper;

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

}
