package com.system.exam.controller.exam;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.exam.EnterExamDTO;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.domain.qo.exam.NewExamQO;
import com.system.exam.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
     * @param map
     * @return
     */
    @PostMapping("/submitAnswer")
    public ResponseData<String> submitAnswer(@RequestBody Map<String,String> map) {
        return ResponseDataUtil.buildSuccess("");
    }

}
