package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 考试码和教师号DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CodeAndNumberDTO {
    /**
     * 考试码
     */
    private String code;

    /**
     * 教师号
     */
    private String number;
}
