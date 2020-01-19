package com.system.exam.service.exam.impl;

import com.system.exam.common.ICheckAnswer;
import com.system.exam.common.IUserSession;
import com.system.exam.domain.dto.exam.EnterExamDTO;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.dto.exam.ExamResultDTO;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.exam.AnswerQO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.domain.qo.exam.NewExamQO;
import com.system.exam.mapper.exam.ExamMapper;
import com.system.exam.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 考试业务层实现类
 */
@Service
public class ExamServiceImpl implements ExamService {
    @Resource
    private ExamMapper examMapper;

    @Autowired
    private IUserSession userSession;

    @Autowired
    private ICheckAnswer checkAnswer;

    /**
     * 查询考试码（学生）
     * @param examQO
     * @return
     */
    @Override
    public ExamDTO getExamByCode(ExamQO examQO) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        examQO.setNumber(userDTO.getNumber());
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

    /**
     * 获取未参加且未超过截止时间的考试（学生）
     * @param newExamQO
     * @return
     */
    @Override
    public List<ExamDTO> pageNewExam(NewExamQO newExamQO) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        newExamQO.setNumber(userDTO.getNumber());
        return examMapper.pageNewExam(newExamQO);
    }

    /**
     * 近期（5条）考试成绩 （学生）
     * @return
     */
    @Override
    public List<ExamDTO> listNewScore() {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        List<ExamDTO> list =  examMapper.listNewScore(userDTO.getNumber());
        return list;
    }

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    @Override
    public EnterExamDTO enterExam(ExamQO examQO) {
        EnterExamDTO enterExamDTO = examMapper.enterExam(examQO);
        if (enterExamDTO != null) {
            examQO.setType("1");
            enterExamDTO.setSingleList(examMapper.listQuestion(examQO));
            examQO.setType("2");
            enterExamDTO.setMultipleList(examMapper.listQuestion(examQO));
        }
        return enterExamDTO;
    }

    /**
     * 提交答案
     * @param answerQO
     * @return
     */
    @Override
    public ExamResultDTO submitAnswer(AnswerQO answerQO) {
        //获取正确答案
        ExamResultDTO examResultDTO = new ExamResultDTO();
        ExamQO examQO = new ExamQO(answerQO.getExamCode());
        examQO.setType("1");
        examResultDTO.setSingleAnswerList(examMapper.listAnswer(examQO));
        examQO.setType("2");
        examResultDTO.setMultipleAnswerList(examMapper.listAnswer(examQO));
        checkAnswer.examResult(examResultDTO,answerQO);
        return examResultDTO;
    }

}
