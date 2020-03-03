package com.system.exam.mapper.user;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.dto.user.PageStudentDTO;
import com.system.exam.domain.dto.user.PageTeacherDTO;
import com.system.exam.domain.qo.user.InsertQO;
import com.system.exam.domain.qo.user.IsNumberQO;
import com.system.exam.domain.qo.user.PageUserQO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 管理mapper
 */
@Mapper
public interface ManageMapper {
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
     * @param insertQO
     * @return
     */
    int insert(InsertQO insertQO);

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
