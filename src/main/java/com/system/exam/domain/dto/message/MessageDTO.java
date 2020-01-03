package com.system.exam.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 查询用户消息DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageDTO {
    /**
     * 消息id
     */
    private String id;

    /**
     * 发送方/接收人 类别 admin/teacher/student
     */
    private String userType;

    /**
     * 发送方/接收人 账号
     */
    private String userNumber;

    /**
     * 发送方/接收人 姓名
     */
    private String userName;

    /**
     * 发送方/接收人 所属学院
     */
    private String collegeName;

    /**
     * 发送方/接收人 班级号
     */
    private String clazzNumber;

    /**
     * 发送时间
     */
    private String time;

}
