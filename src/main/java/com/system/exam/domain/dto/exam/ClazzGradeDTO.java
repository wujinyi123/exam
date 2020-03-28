package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClazzGradeDTO {
    /**
     * 考试截止时间
     */
    private String expTime;

    /**
     * 试卷总分
     */
    private String examScore;

    /**
     * 班级平均分
     */
    private String avgScore;

    /**
     * 班级人数
     */
    private String clazzSize;

    /**
     * 完成人数
     */
    private String finishSize;
}
