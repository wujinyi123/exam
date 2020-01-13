package com.system.exam.domain.qo.exam;

import com.system.exam.common.PageQO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新考试QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewExamQO extends PageQO {
    /**
     * 学号
     */
    private String number;

}
