package com.system.exam.domain.dto.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 考试结果DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamResultDTO {
    /**
     * 得分
     */
    private String score;

    /**
     * 单选题目答案
     */
    private List<AnswerDTO> singleAnswerList;

    /**
     * 多选题目答案
     */
    private List<AnswerDTO> multipleAnswerList;

}
