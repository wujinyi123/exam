package com.system.exam.controller.exam;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.exam.ExamDTO;
import com.system.exam.domain.qo.exam.ExamQO;
import com.system.exam.service.exam.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
     * 查询考试码
     * @param examQO
     * @return
     */
    @PostMapping("/getExamByCode")
    public ResponseData<ExamDTO> getExamByCode(@RequestBody @Valid ExamQO examQO) {
        return ResponseDataUtil.buildSuccess(examService.getExamByCode(examQO));
    }

}