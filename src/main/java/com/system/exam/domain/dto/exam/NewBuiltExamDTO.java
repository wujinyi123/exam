package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新建考试DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewBuiltExamDTO {
    /**
     * 考试码
     */
    private String examCode;
}
