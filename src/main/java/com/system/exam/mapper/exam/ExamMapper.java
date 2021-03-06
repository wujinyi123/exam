package com.system.exam.mapper.exam;

import com.system.exam.domain.dto.exam.*;
import com.system.exam.domain.qo.exam.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 获取未参加且未超过截止时间的考试
     * @param newExamQO
     * @return
     */
    List<ExamDTO> pageNewExam(NewExamQO newExamQO);

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    EnterExamDTO enterExam(ExamQO examQO);

    /**
     * 根据题目类型，得到题目集合
     * @param examQO
     * @return
     */
    List<QuestionDTO> listQuestion(ExamQO examQO);

    /**
     * 题目的正确答案
     * @param examQO
     * @return
     */
    List<AnswerDTO> listAnswer(ExamQO examQO);

    /**
     * 上传考试结果
     * @param answerQO
     * @return
     */
    int submitAnswer(AnswerQO answerQO);

    /**
     * 是否已参加考试
     * @param examQO
     * @return
     */
    int checkExam(ExamQO examQO);

    /**
     * 得到考生答案
     * @param examQO
     * @return
     */
    StuAnsDTO getStuAns(ExamQO examQO);

    /**
     * 更新考生成绩
     * @param examQO
     * @return
     */
    int updateScore(ExamQO examQO);

    /**
     * 教师分页查询考试
     * @param pageExamQO
     * @return
     */
    List<PageExamDTO> pageExam(PageExamQO pageExamQO);

    /**
     * 是否已通知
     * @param examNoticeQO
     * @return
     */
    int countNotice(ExamNoticeQO examNoticeQO);

    /**
     * 考试通知
     * @param examNoticeQO
     * @return
     */
    int examNotice(ExamNoticeQO examNoticeQO);

    /**
     * 查看试卷
     * @param examCode
     * @return
     */
    ExamInfoDTO getExamInfo(String examCode);

    /**
     * 删除小测
     * @param examCode
     * @return
     */
    int deleteExam(String examCode);
    int deleteMessage(String examCode);
    int deleteAnswer(String examCode);
    int deleteQuestion(String examCode);

    /**
     * 班级成绩
     * @param pageGradeQO
     * @return
     */
    List<PageGradeDTO> pageGrade(PageGradeQO pageGradeQO);

    /**
     * 班级考试情况
     * @param clazzGradeQO
     * @return
     */
    ClazzGradeDTO clazzGrade(ClazzGradeQO clazzGradeQO);

    /**
     * 最新成绩
     * @param teacherNumber
     * @return
     */
    List<NewStuScoreDTO> newStuScore(String teacherNumber);

    /**
     * 全班成绩
     * @param clazzGradeQO
     * @return
     */
    List<ExportClazzGradeDTO> exportClazzGrade(ClazzGradeQO clazzGradeQO);

    /**
     * 成绩表
     * @param clazzGradeQO
     * @return
     */
    List<ExportClazzGradeDTO> exportGrade(ClazzGradeQO clazzGradeQO);

    /**
     * 校验考试码是否存在
     * @param examCode
     * @return
     */
    int checkCode(String examCode);

    /**
     * 添加考试
     * @param newBuiltExamQO
     * @return
     */
    int insertExam(NewBuiltExamQO newBuiltExamQO);

    /**
     * 添加题目
     * @param newBuiltQuestionQO
     * @return
     */
    int insertQuestion(NewBuiltQuestionQO newBuiltQuestionQO);

    /**
     * 删除新建的试卷
     * @param examCode
     * @return
     */
    int deleteNewExam(String examCode);
    int deleteNewQuestion(String examCode);

    /**
     * 成绩统计
     * @param pageStuScoreQO
     * @return
     */
    List<ExamDTO> pageStuScore(PageStuScoreQO pageStuScoreQO);
}
