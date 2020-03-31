package com.system.exam.domain.qo.exam;

import com.system.exam.common.PageQO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 成绩统计
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageStuScoreQO extends PageQO {
    /**
     * 学号
     */
    private String stuNumber;

    /**
     * 模糊条件
     */
    private String term;
}
