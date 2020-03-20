package com.system.exam.domain.qo.exam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 新建考试QO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewBuiltExamQO {
    /**
     * 考试码
     */
    @NotBlank(message = "考试码不能为空")
    private String examCode;

    /**
     * 考试名称
     */
    @NotBlank(message = "考试名称不能为空")
    private String examName;

    /**
     * 教师号
     */
    @NotBlank(message = "教师号不能为空")
    private String teacherNumber;

    /**
     * 总分
     */
    @NotBlank(message = "总分不能为空")
    private String score;

    /**
     * 考试时间
     */
    @NotBlank(message = "考试时间不能为空")
    private String time;

    /**
     * 截止时间
     */
    @NotBlank(message = "截止时间不能为空")
    private String expTime;

    /**
     * 单选题集合
     */
    private List<NewBuiltQuestionQO> singleList;

    /**
     * 多选题集合
     */
    private List<NewBuiltQuestionQO> multipleList;

}
