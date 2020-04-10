package com.system.exam.controller.user;

import com.system.exam.common.PageQO;
import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.*;
import com.system.exam.service.user.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

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
     * 全校情况
     * @return
     */
    @PostMapping("/getSchoolInfo")
    public ResponseData<SchoolInfoDTO> getSchoolInfo() {
        return ResponseDataUtil.buildSuccess(manageService.getSchoolInfo());
    }

    /**
     * 分页学院
     * @param pageQO
     * @return
     */
    @GetMapping("/pageCollege")
    public ResponseData<List<PageCollegeDTO>> pageCollege(PageQO pageQO) {
        return ResponseDataUtil.buildSuccess(manageService.pageCollege(), pageQO);
    }

    /**
     * 分页查询班级
     * @param pageClazzQO
     * @return
     */
    @GetMapping("/pageClazz")
    public ResponseData<List<PageClazzDTO>> pageClazz(@Valid PageClazzQO pageClazzQO) {
        return ResponseDataUtil.buildSuccess(manageService.pageClazz(pageClazzQO), pageClazzQO);
    }

    /**
     * 分页查询教师
     * @param pageUserQO
     * @return
     */
    @GetMapping("/pageTeacher")
    public ResponseData<List<PageTeacherDTO>> pageTeacher(@Valid PageUserQO pageUserQO) {
        return ResponseDataUtil.buildSuccess(manageService.pageTeacher(pageUserQO), pageUserQO);
    }

    /**
     * 分页查询学生
     * @param pageUserQO
     * @return
     */
    @GetMapping("/pageStudent")
    public ResponseData<List<PageStudentDTO>> pageStudent(@Valid PageUserQO pageUserQO) {
        return ResponseDataUtil.buildSuccess(manageService.pageStudent(pageUserQO), pageUserQO);
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
    @GetMapping("/insertError")
    public void insertError(@Valid InsertErrorQO insertErrorQO, HttpServletResponse response) {
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

    /**
     * 新一届班级
     * @param response
     */
    @GetMapping("/newClazz")
    public void newClazz(HttpServletResponse response) {
        manageService.newClazz(response);
    }

    /**
     * 新一届学生学号
     * @param list
     * @return
     */
    @PostMapping("/newStuNumber")
    public ResponseData<String> newStuNumber(@RequestBody @Valid List<NewStuNumberQO> list) {
        return ResponseDataUtil.buildSuccess(manageService.newStuNumber(list));
    }

    /**
     * 新一届学生学号
     * @param response
     * @param uuid
     */
    @GetMapping("/newStuNum")
    public void newStuNum(HttpServletResponse response,String uuid) {
        manageService.newStuNum(response,uuid);
    }

    /**
     * 毕业操作
     * @param graduatedQO
     * @return
     */
    @PostMapping("/graduated")
    public ResponseData<String> graduated(@RequestBody @Valid GraduatedQO graduatedQO) {
        return ResponseDataUtil.buildSuccess(manageService.graduated(graduatedQO));
    }
}
