package com.system.exam.service.common;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.common.MbQO;

import java.util.List;

/**
 * 码表业务层接口类
 */
public interface MbService {
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
