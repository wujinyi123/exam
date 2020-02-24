package com.system.exam.service.user;

import com.system.exam.domain.dto.user.InsertUserDTO;
import com.system.exam.domain.qo.user.InsertUserQO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 管理业务层接口类
 */
public interface ManageService {
    /**
     * 导入用户信息
     * @param file
     * @param insertUserQO
     * @return
     */
    InsertUserDTO insertUser(MultipartFile file, InsertUserQO insertUserQO);
}
