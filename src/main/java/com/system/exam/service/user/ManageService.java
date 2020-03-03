package com.system.exam.service.user;

import com.system.exam.domain.dto.user.InsertDTO;
import com.system.exam.domain.dto.user.PageStudentDTO;
import com.system.exam.domain.dto.user.PageTeacherDTO;
import com.system.exam.domain.qo.user.InsertErrorQO;
import com.system.exam.domain.qo.user.InsertQO;
import com.system.exam.domain.qo.user.PageUserQO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 管理业务层接口类
 */
public interface ManageService {
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
     * 下载模板
     * @param response
     * @param type
     */
    void downloadTemplate(HttpServletResponse response, String type);

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
}
