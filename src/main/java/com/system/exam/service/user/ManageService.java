package com.system.exam.service.user;

import com.system.exam.domain.dto.user.InsertDTO;
import com.system.exam.domain.qo.user.InsertErrorQO;
import com.system.exam.domain.qo.user.InsertQO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 管理业务层接口类
 */
public interface ManageService {
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
