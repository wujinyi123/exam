package com.system.exam.service.common.impl;

import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.common.ClazzQO;
import com.system.exam.domain.qo.common.MbQO;
import com.system.exam.mapper.common.MbMapper;
import com.system.exam.service.common.MbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 码表业务层实现类
 */
@Service
public class MbServiceImpl implements MbService {
    @Resource
    private MbMapper mbMapper;

    @Autowired
    private IUserSession userSession;

    /**
     * 考试码（教师端）
     * @return
     */
    @Override
    public List<MbDTO> listExam() {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("teacherExamSystem");
        return mbMapper.listExam(userDTO.getNumber());
    }

    /**
     * 所有学院
     * @return
     */
    @Override
    public List<MbDTO> listCollege() {
        return mbMapper.listCollege();
    }

    /**
     * 学院所有班级
     * @param code
     * @return
     */
    @Override
    public List<MbDTO> listClazz(String code) {
        return mbMapper.listClazz(code);
    }

    @Override
    public List<MbDTO> listClazzByCY(ClazzQO clazzQO) {
        return mbMapper.listClazzByCY(clazzQO);
    }

    /**
     * 学院和年级
     * @return
     */
    @Override
    public Map<String, List<MbDTO>> collegeAndYear() {
        Map<String,List<MbDTO>> map = new HashMap<>();
        map.put("college",mbMapper.listCollege());
        map.put("year",mbMapper.listYear());
        return map;
    }

}
