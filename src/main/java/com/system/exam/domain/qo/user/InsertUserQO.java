package com.system.exam.domain.qo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 导入用户信息QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertUserQO {
    /**
     * 类型：教师/学生
     */
    private String type;

    /**
     * 数据组
     */
    private List<String> dataList;
}
