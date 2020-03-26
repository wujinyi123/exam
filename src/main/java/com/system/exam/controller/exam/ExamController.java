package com.system.exam.controller.exam;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import com.system.exam.domain.dto.exam.*;
import com.system.exam.domain.qo.exam.*;
import com.system.exam.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * 考试
 */
@Validated
@RestController
@RequestMapping("/back/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    /**
     * 查询考试码（学生）
     * @param examQO
     * @return
     */
    @PostMapping("/getExamByCode")
    public ResponseData<ExamDTO> getExamByCode(@RequestBody @Valid ExamQO examQO) {
        return ResponseDataUtil.buildSuccess(examService.getExamByCode(examQO));
    }

    /**
     * 获取未参加且未超过截止时间的考试（学生）
     * @return
     */
    @GetMapping("/pageNewExam")
    public ResponseData<List<ExamDTO>> pageNewExam(NewExamQO newExamQO) {
        return ResponseDataUtil.buildSuccess(examService.pageNewExam(newExamQO), newExamQO);
    }

    /**
     * 近期（5条）考试成绩 （学生）
     * @return
     */
    @PostMapping("/listNewScore")
    public ResponseData<List<ExamDTO>> listNewScore() {
        return ResponseDataUtil.buildSuccess(examService.listNewScore());
    }

    /**
     * 进入考试
     * @param examQO
     * @return
     */
    @PostMapping("/enterExam")
    public ResponseData<EnterExamDTO> enterExam(@RequestBody @Valid ExamQO examQO) {
        return ResponseDataUtil.buildSuccess(examService.enterExam(examQO));
    }

    /**
     * 提交答案
     * @param answerQO
     * @return
     */
    @PostMapping("/submitAnswer")
    public ResponseData<ExamResultDTO> submitAnswer(@Valid AnswerQO answerQO) {
        return ResponseDataUtil.buildSuccess(examService.submitAnswer(answerQO));
    }

    /**
     * 是否已参加考试
     * @param examQO
     * @return
     */
    @PostMapping("/checkExam")
    public ResponseData<String> checkExam(@RequestBody @Valid ExamQO examQO) {
        return ResponseDataUtil.buildSuccess(examService.checkExam(examQO));
    }

    /**
     * 得到考试结果
     * @param examQO
     * @return
     */
    @PostMapping("/getExamResult")
    public ResponseData<ExamResultDTO> getExamResult(@RequestBody @Valid ExamQO examQO) {
        return ResponseDataUtil.buildSuccess(examService.getExamResult(examQO));
    }

    /**
     * 新建考试
     * @param newBuiltExamQO
     * @return
     */
    @PostMapping("/newBuiltExam")
    public ResponseData<NewBuiltExamDTO> newBuiltExam(@RequestBody @Valid NewBuiltExamQO newBuiltExamQO) {
        return ResponseDataUtil.buildSuccess(examService.newBuiltExam(newBuiltExamQO));
    }

    /**
     * 得到一个新的考试码
     * @return
     */
    @PostMapping("/getNewExamCode")
    public ResponseData<CodeAndNumberDTO> getNewExamCode() {
        return ResponseDataUtil.buildSuccess(examService.getNewExamCode());
    }

    /**
     * 上传图片
     * @param file
     * @param imgUrl
     * @return
     */
    @PostMapping("/imgUpload")
    public ResponseData<ImgUploadDTO> imgUpload(@RequestParam(value = "file") MultipartFile file, @RequestParam String imgUrl) {
        return ResponseDataUtil.buildSuccess(examService.imgUpload(file,imgUrl));
    }

    /**
     * 删除图片
     * @param imgUrl
     * @return
     */
    @PostMapping("/deleteImg")
    public ResponseData<String> deleteImg(@RequestParam String imgUrl) {
        return ResponseDataUtil.buildSuccess(examService.deleteImg(imgUrl));
    }

    /**
     * 考试通知
     * @param examNoticeQO
     * @return
     */
    @PostMapping("/examNotice")
    public ResponseData<String> examNotice(@RequestBody @Valid ExamNoticeQO examNoticeQO) {
        return ResponseDataUtil.buildSuccess(examService.examNotice(examNoticeQO));
    }

    /**
     * 教师分页查询考试
     * @param pageExamQO
     * @return
     */
    @GetMapping("/pageExam")
    public ResponseData<List<PageExamDTO>> pageExam(@Valid PageExamQO pageExamQO) {
        return ResponseDataUtil.buildSuccess(examService.pageExam(pageExamQO), pageExamQO);
    }
}
