package com.system.exam.controller.common;

import com.system.exam.common.ResponseData;
import com.system.exam.common.ResponseDataUtil;
import com.system.exam.domain.dto.common.TreeDTO;
import com.system.exam.service.common.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 树形组件
 */
@Validated
@RestController
@RequestMapping("/back/tree")
public class TreeController {
    @Autowired
    private TreeService treeService;

    /**
     * 学生树
     * @return
     */
    @PostMapping("/listStuTree")
    public ResponseData<List<TreeDTO>> listStuTree() {
        return ResponseDataUtil.buildSuccess(treeService.listStuTree());
    }

    /**
     * 班级树
     * @return
     */
    @PostMapping("/listClazzTree")
    public ResponseData<List<TreeDTO>> listClazzTree() {
        return ResponseDataUtil.buildSuccess(treeService.listClazzTree());
    }

}
