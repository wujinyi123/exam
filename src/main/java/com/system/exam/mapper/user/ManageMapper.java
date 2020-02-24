package com.system.exam.mapper.user;

import com.system.exam.domain.qo.user.InsertUserQO;
import com.system.exam.domain.qo.user.IsUserQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理mapper
 */
@Mapper
public interface ManageMapper {
    /**
     * 导入用户信息
     * @param insertUserQO
     * @return
     */
    int insertUser(InsertUserQO insertUserQO);

    /**
     * 所有学院代码
     * @return
     */
    List<String> listCollegeCode();

    /**
     * 所有班级代码
     * @return
     */
    List<String> listClazzNumber();

    /**
     * 查询账户是否存在
     * @param isUserQO
     * @return
     */
    int isNumber(IsUserQO isUserQO);
}
