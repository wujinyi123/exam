package com.system.exam.controller.user;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.user.InsertUserDTO;
import com.system.exam.domain.qo.user.InsertUserQO;
import com.system.exam.service.user.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
     * 导入用户信息
     * @param file
     * @param insertUserQO
     * @return
     */
    @PostMapping("/insertUser")
    public ResponseData<InsertUserDTO> insertUser(@RequestParam("file") MultipartFile file, InsertUserQO insertUserQO) {
        return ResponseDataUtil.buildSuccess(manageService.insertUser(file,insertUserQO));
    }
}
