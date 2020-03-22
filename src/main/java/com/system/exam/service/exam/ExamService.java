package com.system.exam.service.exam;

import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.exam.*;
import com.system.exam.domain.qo.exam.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 考试业务层接口类
 */
public interface ExamService {
    /**
     * 查询考试码（学生）
     * @param examQO
     * @return
     */
    ExamDTO getExamByCode(ExamQO examQO);

    /**
     * 获取未参加且未超过截止时间的考试（学生）
     * @param newExamQO
     * @return
     */
    List<ExamDTO> pageNewExam(NewExamQO newExamQO);

    /**
     * 近期（5条）考试成绩 （学生）
     * @return
     */
    List<ExamDTO> listNewScore();

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    EnterExamDTO enterExam(ExamQO examQO);

    /**
     * 提交答案
     * @param answerQO
     * @return
     */
    ExamResultDTO submitAnswer(AnswerQO answerQO);

    /**
     * 是否已参加考试
     * @param examQO
     * @return
     */
    String checkExam(ExamQO examQO);

    /**
     * 得到考试结果
     * @param examQO
     * @return
     */
    ExamResultDTO getExamResult(ExamQO examQO);

    /**
     * 新建考试
     * @param newBuiltExamQO
     * @return
     */
    NewBuiltExamDTO newBuiltExam(NewBuiltExamQO newBuiltExamQO);

    /**
     * 得到一个新的考试码
     * @return
     */
    CodeAndNumberDTO getNewExamCode();

    /**
     * 上传图片
     * @param file
     * @param imgUrl
     * @return
     */
    ImgUploadDTO imgUpload(MultipartFile file, String imgUrl);

    /**
     * 教师分页查询考试
     * @param pageExamQO
     * @return
     */
    List<PageExamDTO> pageExam(PageExamQO pageExamQO);
}
