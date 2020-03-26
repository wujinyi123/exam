package com.system.exam.controller.common;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.common.MbQO;
import com.system.exam.service.common.MbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 码表
 */
@Validated
@RestController
@RequestMapping("/back/mb")
public class MbController {
    @Autowired
    private MbService mbService;

    /**
     * 考试码（教师端）
     * @param mbQO
     * @return
     */
    @PostMapping("/listExam")
    public ResponseData<List<MbDTO>> listExam(@RequestBody @Valid MbQO mbQO) {
        return ResponseDataUtil.buildSuccess(mbService.listExam(mbQO));
    }

    /**
     * 所有学院
     * @return
     */
    @PostMapping("/listCollege")
    public ResponseData<List<MbDTO>> listCollege() {
        return ResponseDataUtil.buildSuccess(mbService.listCollege());
    }

    /**
     * 学院所有班级
     * @param code
     * @return
     */
    @PostMapping("/listClazz")
    public ResponseData<List<MbDTO>> listClazz(@RequestParam String code) {
        return ResponseDataUtil.buildSuccess(mbService.listClazz(code));
    }

}
