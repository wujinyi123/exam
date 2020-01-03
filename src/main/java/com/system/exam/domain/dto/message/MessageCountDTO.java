package com.system.exam.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户消息条数DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageCountDTO {
    /**
     * 新消息
     */
    private String newSum;

    /**
     * 已发消息
     */
    private String sendSum;

    /**
     * 收信箱
     */
    private String receiverSum;

}
