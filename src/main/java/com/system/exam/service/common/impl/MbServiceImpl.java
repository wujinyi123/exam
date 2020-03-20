package com.system.exam.service.common.impl;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.common.MbQO;
import com.system.exam.mapper.common.MbMapper;
import com.system.exam.service.common.MbService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 码表业务层实现类
 */
@Service
public class MbServiceImpl implements MbService {
    @Resource
    private MbMapper mbMapper;

    /**
     * 考试码（教师端）
     * @param mbQO
     * @return
     */
    @Override
    public List<MbDTO> listExam(MbQO mbQO) {
        return mbMapper.listExam(mbQO);
    }

    /**
     * 所有学院
     * @return
     */
    @Override
    public List<MbDTO> listCollege() {
        return mbMapper.listCollege();
    }

}
