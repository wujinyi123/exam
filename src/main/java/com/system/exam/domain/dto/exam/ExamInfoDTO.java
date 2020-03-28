package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamInfoDTO {
    /**
     * 考试码
     */
    private String examCode;

    /**
     * 小测名称
     */
    private String examName;

    /**
     * 总分
     */
    private String score;

    /**
     * 时间
     */
    private String time;

    /**
     * 开始时间
     */
    private String pdTime;

    /**
     * 结束时间
     */
    private String expTime;

    /**
     * 单选题
     */
    private List<QuestionDTO> singleList;

    /**
     * 多选题
     */
    private List<QuestionDTO> multipleList;
}
