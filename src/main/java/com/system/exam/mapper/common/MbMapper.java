package com.system.exam.mapper.common;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.common.MbQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 码表mapper
 */
@Mapper
public interface MbMapper {
    /**
     * 考试码（教师端）
     * @param mbQO
     * @return
     */
    List<MbDTO> listExam(MbQO mbQO);

    /**
     * 所有学院
     * @return
     */
    List<MbDTO> listCollege();

}
