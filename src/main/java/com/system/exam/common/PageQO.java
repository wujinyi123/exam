package com.system.exam.common;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class PageQO {
    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    private Integer page;

    /**
     * 每页条数
     */
    @NotNull(message = "每页条数")
    private Integer limit;

}
