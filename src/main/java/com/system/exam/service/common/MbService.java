package com.system.exam.service.common;

import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.common.ClazzQO;
import com.system.exam.domain.qo.common.MbQO;

import java.util.List;
import java.util.Map;

/**
 * 码表业务层接口类
 */
public interface MbService {
    /**
     * 考试码（教师端）
     * @return
     */
    List<MbDTO> listExam();

    /**
     * 所有学院
     * @return
     */
    List<MbDTO> listCollege();

    /**
     * 学院所有班级
     * @param code
     * @return
     */
    List<MbDTO> listClazz(String code);

    /**
     * 根据条件查询班级
     * @param clazzQO
     * @return
     */
    List<MbDTO> listClazzByCY(ClazzQO clazzQO);

    /**
     * 学院和年级
     * @return
     */
    Map<String,List<MbDTO>> collegeAndYear();
}
