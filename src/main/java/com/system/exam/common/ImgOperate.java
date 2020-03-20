package com.system.exam.common;

import com.system.exam.domain.dto.common.ImgUploadDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 图片操作
 */
public interface ImgOperate {
    /**
     * 图片上传
     * @param file
     * @param imgUrl
     * @return
     */
    ImgUploadDTO imgUpload(MultipartFile file, String imgUrl);
}
