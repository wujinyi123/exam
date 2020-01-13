package com.system.exam.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 码表DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MbDTO {
    /**
     * 代码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

}
