package com.system.exam.controller.user;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.user.InsertDTO;
import com.system.exam.domain.qo.user.InsertErrorQO;
import com.system.exam.domain.qo.user.InsertQO;
import com.system.exam.service.user.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理
 */
@Validated
@RestController
@RequestMapping("/back/manage")
public class ManageController {
    @Autowired
    private ManageService manageService;

    /**
     * 导出学院代码、班级号
     * @param response
     */
    @GetMapping("/instructions")
    public void instructions(HttpServletResponse response) {
        manageService.instructions(response);
    }

    /**
     * 导入错误
     * @param insertErrorQO
     * @param response
     */
    @GetMapping("/insertError")
    public void insertError(@RequestBody InsertErrorQO insertErrorQO, HttpServletResponse response) {
        manageService.insertError(insertErrorQO,response);
    }

    /**
     * 导入信息
     * @param file
     * @param insertQO
     * @return
     */
    @PostMapping("/insertUser")
    public ResponseData<InsertDTO> insertUser(@RequestParam("file") MultipartFile file, InsertQO insertQO) {
        return ResponseDataUtil.buildSuccess(manageService.insertUser(file,insertQO));
    }
}
