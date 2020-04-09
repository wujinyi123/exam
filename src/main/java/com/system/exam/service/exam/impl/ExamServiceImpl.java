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
import com.system.exam.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
     * 成绩统计
     * @param pageStuScoreQO
     * @return
     */
    @Override
    public List<ExamDTO> pageStuScore(PageStuScoreQO pageStuScoreQO) {
        if (pageStuScoreQO.getStuNumber()==null || "".equals(pageStuScoreQO.getStuNumber())) {
            //获取当前用户
            UserDTO userDTO = userSession.getUser("studentExamSystem");
            pageStuScoreQO.setStuNumber(userDTO.getNumber());
        }
        return examMapper.pageStuScore(pageStuScoreQO);
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

        if (examQO.getNumber()==null || "".equals(examQO.getNumber())) {
            //获取当前用户
            UserDTO userDTO = userSession.getUser("studentExamSystem");
            examQO.setNumber(userDTO.getNumber());
        }

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

        if (examMapper.insertExam(newBuiltExamQO)==1) {
            newBuiltExamDTO.setState("ok");
            int singleSum = insertQuestion(newBuiltExamQO.getSingleList());
            int multipleSum = insertQuestion(newBuiltExamQO.getMultipleList());
            if (singleSum!=newBuiltExamQO.getSingleList().size() || multipleSum!=newBuiltExamQO.getMultipleList().size()) {
                newBuiltExamDTO.setState("no");
                examMapper.deleteNewExam(newBuiltExamQO.getExamCode());
                examMapper.deleteNewQuestion(newBuiltExamQO.getExamCode());
            }
        } else {
            newBuiltExamDTO.setState("no");
        }

        return newBuiltExamDTO;
    }

    /**
     * 添加题目
     * @param questionList
     * @return
     */
    private int insertQuestion(List<NewBuiltQuestionQO> questionList) {
        int sum = 0;
        for (NewBuiltQuestionQO newBuiltQuestionQO:questionList) {
            if (examMapper.insertQuestion(newBuiltQuestionQO)==1) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * 得到一个新的考试码
     * @return
     */
    @Override
    public CodeAndNumberDTO getNewExamCode() {
        CodeAndNumberDTO codeAndNumberDTO = new CodeAndNumberDTO();
        codeAndNumberDTO.setCode(ExamCodeUtil.getCode());

        while (examMapper.checkCode(codeAndNumberDTO.getCode())!=0) {
            codeAndNumberDTO.setCode(ExamCodeUtil.getCode());
        }

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

    /**
     * 查看试卷
     * @param examCode
     * @return
     */
    @Override
    public ExamInfoDTO getExamInfo(String examCode) {
        ExamInfoDTO examInfoDTO = examMapper.getExamInfo(examCode);
        ExamQO examQO = ExamQO.builder().examCode(examCode).type("1").build();
        examInfoDTO.setSingleList(examMapper.listQuestion(examQO));
        examQO.setType("2");
        examInfoDTO.setMultipleList(examMapper.listQuestion(examQO));
        return examInfoDTO;
    }

    /**
     * 删除小测
     * @param examCode
     * @return
     */
    @Override
    public String deleteExam(String examCode) {
        examMapper.deleteMessage(examCode);
        examMapper.deleteAnswer(examCode);
        examMapper.deleteQuestion(examCode);
        String result = examMapper.deleteExam(examCode)+"";
        return result;
    }

    /**
     * 班级成绩
     * @param pageGradeQO
     * @return
     */
    @Override
    public List<PageGradeDTO> pageGrade(PageGradeQO pageGradeQO) {
        return examMapper.pageGrade(pageGradeQO);
    }

    /**
     * 导出成绩
     * @param response
     * @param clazzGradeQO
     */
    @Override
    public void exportClazzGrade(HttpServletResponse response, ClazzGradeQO clazzGradeQO) {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("考试码");
        listTitle.add("小测名称");
        listTitle.add("总分");
        listTitle.add("学院");
        listTitle.add("班级");
        listTitle.add("专业");
        listTitle.add("学号");
        listTitle.add("姓名");
        listTitle.add("成绩");
        listTitle.add("用时");
        listTitle.add("提交时间");

        List<ExportClazzGradeDTO> list = null;

        if ("all".equals(clazzGradeQO.getClazzNumber())) {
            list = examMapper.exportGrade(clazzGradeQO);
        } else {
            list = examMapper.exportClazzGrade(clazzGradeQO);
        }

        List<List<String>> datas = new ArrayList<>();

        List<String> dataList = null;
        for (ExportClazzGradeDTO dto:list) {
            dataList = new ArrayList<>();
            dataList.add(dto.getExamCode());
            dataList.add(dto.getExamName());
            dataList.add(dto.getScore());
            dataList.add(dto.getCollege());
            dataList.add(dto.getClazz());
            dataList.add(dto.getMajor());
            dataList.add(dto.getStuNumber());
            dataList.add(dto.getStuName());
            dataList.add(dto.getStuScore());
            dataList.add(dto.getUseTime());
            dataList.add(dto.getSubmitTime());
            datas.add(dataList);
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(clazzGradeQO.getClazzNumber()+"成绩"+".xlsx", "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(clazzGradeQO.getClazzNumber()+"成绩",listTitle,datas);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 班级考试情况
     * @param clazzGradeQO
     * @return
     */
    @Override
    public ClazzGradeDTO clazzGrade(ClazzGradeQO clazzGradeQO) {
        return examMapper.clazzGrade(clazzGradeQO);
    }

    /**
     * 最新成绩
     * @param teacherNumber
     * @return
     */
    @Override
    public List<NewStuScoreDTO> newStuScore(String teacherNumber) {
        if (teacherNumber==null || "".equals(teacherNumber)) {
            //获取当前用户
            UserDTO userDTO = userSession.getUser("teacherExamSystem");
            teacherNumber = userDTO.getNumber();
        }
        return examMapper.newStuScore(teacherNumber);
    }

    /**
     * 导入选择题
     * @param file
     * @param importExamQO
     * @return
     */
    @Override
    public ImportExamDTO importExam(MultipartFile file, ImportExamQO importExamQO) {
        ImportExamDTO importExamDTO = new ImportExamDTO();

        //判断是否上传了文件
        if (file.isEmpty()) {
            importExamDTO.setMsg("请上传文件");
            return importExamDTO;
        }

        //读取文件内容
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            ExcelUtil.getExamByExcel(inputStream,file.getOriginalFilename(),importExamDTO,importExamQO);
            inputStream.close();
        } catch (Exception e) {
            importExamDTO.setMsg("文件获取失败："+e);
            return importExamDTO;
        }

        return importExamDTO;
    }
}
