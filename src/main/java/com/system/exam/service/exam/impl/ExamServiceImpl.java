package com.system.exam.service.exam.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.mapper.exam.ExamMapper;
import com.system.exam.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 考试业务层实现类
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamMapper examMapper;

    @Autowired
    private IUserSession userSession;

    /**
     * 查询考试码
     * @param examQO
     * @return
     */
    @Override
    public ExamDTO getExamByCode(ExamQO examQO) {
        if ("student".equals(examQO.getUserType())) {
            examQO.setNumber(((UserDTO)userSession.getUser("studentExamSystem")).getNumber());
        } else {
            examQO.setNumber(" ");
        }
        ExamDTO examDTO = examMapper.getExamByCode(examQO);
        if (examDTO==null) {
            examDTO = new ExamDTO();
            examDTO.setIsFlag("no");
            examDTO.setMsg("考试码不存在");
        } else {
            examDTO.setIsFlag("ok");
        }
        return examDTO;
    }

}
