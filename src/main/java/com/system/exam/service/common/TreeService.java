package com.system.exam.service.common;

import com.system.exam.domain.dto.common.TreeDTO;

import java.util.List;

/**
 * 树形组件业务层接口类
 */
public interface TreeService {
    /**
     * 学生树
     */
    List<TreeDTO> listStuTree();

    /**
     * 班级树
     */
    List<TreeDTO> listClazzTree();

}
