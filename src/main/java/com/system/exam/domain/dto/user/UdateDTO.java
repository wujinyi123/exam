package com.system.exam.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 更新信息状态DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UdateDTO {
    /**
     * 状态信息  ok：成功，no：失败
     */
    private String state;

    /**
     * 更新信息
     */
    private String msg;

}
