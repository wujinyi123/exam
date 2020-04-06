package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * 导入小测QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImportExamQO {
    /**
     * 单选题数
     */
    @NotBlank(message = "单选题数不能为空")
    private String singles;

    /**
     * 多选题数
     */
    @NotBlank(message = "多选题数不能为空")
    private String multiples;
}
