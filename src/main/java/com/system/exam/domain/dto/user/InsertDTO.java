package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 导入用户情况DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertDTO implements Serializable {
    /**
     * 类型
     */
    private String type;

    /**
     * 文件信息
     */
    private String msg;

    /**
     * 作为Redis的key值
     */
    private String uuid;

    /**
     * 数据组
     */
    private List<List<String>> dataList;

    /**
     * 成功数
     */
    private String success;

    /**
     * 失败数
     */
    private String fail;

}
