package com.system.exam.service.common.impl;

import com.system.exam.domain.dto.common.TreeDTO;
import com.system.exam.mapper.common.TreeMapper;
import com.system.exam.service.common.TreeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 树形组件业务层实现类
 */
@Service
public class TreeServiceImpl implements TreeService {
    @Resource
    private TreeMapper treeMapper;

    /**
     * 学生树
     * @return
     */
    @Override
    public List<TreeDTO> listStuTree() {
        return treeMapper.listStuTree();
    }

    /**
     * 班级树
     * @return
     */
    @Override
    public List<TreeDTO> listClazzTree() {
        return treeMapper.listClazzTree();
    }

}
