package com.system.exam.mapper.common;

import com.system.exam.domain.dto.common.TreeDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 树形组件mapper
 */
@Mapper
public interface TreeMapper {
    /**
     * 学生树
     */
    List<TreeDTO> listStuTree();

    /**
     * 班级树
     */
    List<TreeDTO> listClazzTree();

}
