package com.system.exam.service.user;

import com.system.exam.common.PageQO;
import com.system.exam.common.ResultEnums;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理业务层接口类
 */
public interface ManageService {
    /**
     * 全校情况
     * @return
     */
    SchoolInfoDTO getSchoolInfo();

    /**
     * 分页学院
     * @return
     */
    List<PageCollegeDTO> pageCollege();

    /**
     * 分页查询班级
     * @param pageClazzQO
     * @return
     */
    List<PageClazzDTO> pageClazz(PageClazzQO pageClazzQO);

    /**
     * 分页查询教师
     * @param pageUserQO
     * @return
     */
    List<PageTeacherDTO> pageTeacher(PageUserQO pageUserQO);

    /**
     * 分页查询学生
     * @param pageUserQO
     * @return
     */
    List<PageStudentDTO> pageStudent(PageUserQO pageUserQO);

    /**
     * 导入信息
     * @param file
     * @param insertQO
     * @return
     */
    InsertDTO insert(MultipartFile file, InsertQO insertQO);

    /**
     * 导出学院代码、班级号
     * @param response
     */
    void instructions(HttpServletResponse response);

    /**
     * 导入错误
     * @param insertErrorQO
     * @param response
     */
    void insertError(InsertErrorQO insertErrorQO, HttpServletResponse response);

    /**
     * 新一届班级
     * @param response
     */
    void newClazz(HttpServletResponse response);

    /**
     * 新一届学生学号
     * @param list
     * @return
     */
    String newStuNumber(List<NewStuNumberQO> list);

    /**
     * 新一届学生学号
     * @param response
     * @param uuid
     */
    void newStuNum(HttpServletResponse response,String uuid);
}
