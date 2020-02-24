package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 导入用户情况DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InsertUserDTO {
    /**
     * 文件信息
     */
    private String msg;

    /**
     * 数据组
     */
    private List<List<String>> dataList;

    /**
     * 添加是否成功
     */
    private List<String> flag;

    /**
     * 添加反馈信息
     */
    private List<String> dataMsg;

    /**
     * 成功数
     */
    private String success;

    /**
     * 失败数
     */
    private String fail;

}
