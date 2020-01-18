package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 进入考试DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnterExamDTO {
    /**
     * 考试码
     */
    private String examCode;

    /**
     * 考试名
     */
    private String examName;

    /**
     * 总分
     */
    private String score;

    /**
     * 考试时间
     */
    private String time;

    /**
     * 单选总分
     */
    private String singleScore;

    /**
     * 多选总分
     */
    private String multipleScore;

    /**
     * 单选题
     */
    private List<QuestionDTO> singleList;

    /**
     * 多选题
     */
    private List<QuestionDTO> multipleList;

}
