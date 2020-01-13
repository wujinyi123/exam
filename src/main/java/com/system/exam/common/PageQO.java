package com.system.exam.common;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageQO {
    /**
     * 页码
     */
    private Integer page = 1;

    /**
     * 每页条数
     */
    private Integer limit = 10;

}
