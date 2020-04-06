package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 导入小测DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportExamDTO {
    /**
     * 导入情况信息
     */
    private String msg;

    /**
     * 单选题
     */
    List<ImportQuestionDTO> singleList;

    /**
     * 多选题
     */
    List<ImportQuestionDTO> multipleList;
}
