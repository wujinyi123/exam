package com.system.exam.service.exam.impl;

import com.system.exam.common.ICheckAnswer;
import com.system.exam.common.IUserSession;
import com.system.exam.common.ImgOperate;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.exam.*;
import com.system.exam.domain.dto.user.UserDTO;
import com.system.exam.domain.qo.exam.*;
import com.system.exam.mapper.exam.ExamMapper;
import com.system.exam.service.exam.ExamService;
import com.system.exam.util.ExamCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.*;

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

    @Autowired
    private ImgOperate imgOperate;

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
        //核对答案
        checkAnswer.examResult(examResultDTO,answerQO);
        //上传考试结果
        examMapper.submitAnswer(answerQO);
        return examResultDTO;
    }

    /**
     * 是否已参加考试
     * @param examQO
     * @return
     */
    @Override
    public String checkExam(ExamQO examQO) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        examQO.setNumber(userDTO.getNumber());
        if (examMapper.checkExam(examQO) > 0) return "finish";
        else return "enterExam";
    }

    /**
     * 得到考试结果
     * @param examQO
     * @return
     */
    @Override
    public ExamResultDTO getExamResult(ExamQO examQO) {
        //获取正确答案
        ExamResultDTO examResultDTO = new ExamResultDTO();
        examQO.setType("1");
        examResultDTO.setSingleAnswerList(examMapper.listAnswer(examQO));
        examQO.setType("2");
        examResultDTO.setMultipleAnswerList(examMapper.listAnswer(examQO));

        //获取当前用户
        UserDTO userDTO = userSession.getUser("studentExamSystem");
        examQO.setNumber(userDTO.getNumber());

        //考生答案
        StuAnsDTO stuAnsDTO = examMapper.getStuAns(examQO);
        if (stuAnsDTO != null) {
            checkAnswer.examResult(examResultDTO,stuAnsDTO);
            if (!examResultDTO.getScore().equals(stuAnsDTO.getScore())) {
                examQO.setStuScore(examResultDTO.getScore());
                examMapper.updateScore(examQO);
            }
        }
        return examResultDTO;
    }

    /**
     * 新建考试
     * @param newBuiltExamQO
     * @return
     */
    @Override
    public NewBuiltExamDTO newBuiltExam(NewBuiltExamQO newBuiltExamQO) {
        NewBuiltExamDTO newBuiltExamDTO = new NewBuiltExamDTO();
        newBuiltExamDTO.setExamCode(newBuiltExamQO.getExamCode());
        newBuiltExamDTO.setExamName(newBuiltExamQO.getExamName());
        newBuiltExamDTO.setScore(newBuiltExamQO.getScore());
        newBuiltExamDTO.setTime(newBuiltExamQO.getTime());
        newBuiltExamDTO.setExpTime(newBuiltExamQO.getExpTime());

        newBuiltExamDTO.setState("ok");
        return newBuiltExamDTO;
    }

    /**
     * 得到一个新的考试码
     * @return
     */
    @Override
    public CodeAndNumberDTO getNewExamCode() {
        CodeAndNumberDTO codeAndNumberDTO = new CodeAndNumberDTO();
        codeAndNumberDTO.setCode(ExamCodeUtil.getCode());

        //获取当前用户
        UserDTO userDTO = userSession.getUser("teacherExamSystem");
        codeAndNumberDTO.setNumber(userDTO.getNumber());
        return codeAndNumberDTO;
    }

    /**
     * 上传图片
     * @param file
     * @param imgUrl
     * @return
     */
    @Override
    public ImgUploadDTO imgUpload(MultipartFile file, String imgUrl) {
        ImgUploadDTO imgUploadDTO = imgOperate.imgUpload(file,imgUrl);
        return imgUploadDTO;
    }

    @Override
    public String deleteImg(String imgUrl) {
        return imgOperate.deleteImg(imgUrl);
    }

    /**
     * 考试通知
     * @param examNoticeQO
     * @return
     */
    @Override
    public String examNotice(ExamNoticeQO examNoticeQO) {
        if (examMapper.countNotice(examNoticeQO)!=0) {
            return "2";
        }
        if (examMapper.examNotice(examNoticeQO)!=0) {
            return "1";
        }
        return "0";
    }

    /**
     * 教师分页查询考试
     * @param pageExamQO
     * @return
     */
    @Override
    public List<PageExamDTO> pageExam(PageExamQO pageExamQO) {
        //获取当前用户
        UserDTO userDTO = userSession.getUser("teacherExamSystem");
        pageExamQO.setTeacherNumber(userDTO.getNumber());
        return examMapper.pageExam(pageExamQO);
    }

}
