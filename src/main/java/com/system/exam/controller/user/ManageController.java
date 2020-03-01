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
import javax.validation.constraints.NotNull;

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
     * 下载模板
     * @param response
     * @param type
     */
    @GetMapping("/downloadTemplate")
    public void downloadTemplate(HttpServletResponse response, @RequestParam @NotNull String type) {
        manageService.downloadTemplate(response,type);
    }

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
    @PostMapping("/insertError")
    public void insertError(@RequestBody InsertErrorQO insertErrorQO, HttpServletResponse response) {
        manageService.insertError(insertErrorQO,response);
    }

    /**
     * 导入信息
     * @param file
     * @param insertQO
     * @return
     */
    @PostMapping("/insert")
    public ResponseData<InsertDTO> insert(@RequestParam("file") MultipartFile file, InsertQO insertQO) {
        return ResponseDataUtil.buildSuccess(manageService.insert(file,insertQO));
    }
}