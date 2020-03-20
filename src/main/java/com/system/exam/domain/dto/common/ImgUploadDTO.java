package com.system.exam.domain.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 图片上传DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgUploadDTO {
    /**
     * 上传结果代码
     */
    private String code;

    /**
     * 图片地址
     */
    private String imgUrl;
}
