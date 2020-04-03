package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 错误用户QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertErrorQO {
    /**
     * 用户类型
     */
    private String type;

    /**
     * uuid
     */
    private String uuid;

    /**
     * 数据组
     */
    private List<List<String>> dataList;
}
