package com.system.exam.mapper.user;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.qo.user.InsertQO;
import com.system.exam.domain.qo.user.IsNumberQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理mapper
 */
@Mapper
public interface ManageMapper {
    /**
     * 导入用户信息
     * @param insertQO
     * @return
     */
    int insertUser(InsertQO insertQO);

    /**
     * 所有学院
     * @return
     */
    List<MbDTO> listCollege();

    /**
     * 所有班级
     * @return
     */
    List<MbDTO> listClazz();

    /**
     * 查询账户是否存在
     * @param isNumberQO
     * @return
     */
    int isNumber(IsNumberQO isNumberQO);
}
