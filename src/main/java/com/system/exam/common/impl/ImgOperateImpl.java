package com.system.exam.common.impl;

import com.system.exam.common.ImgOperate;
import com.system.exam.domain.dto.common.ImgUploadDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片操作
 */
@Component
public class ImgOperateImpl implements ImgOperate {
    @Value("${imgIp}")
    private String imgIp;

    @Value("${imgPath}")
    private String imgPath;

    /**
     * 上传图片
     * @param file
     * @param imgUrl
     * @return
     */
    @Override
    public ImgUploadDTO imgUpload(MultipartFile file, String imgUrl) {
        ImgUploadDTO imgUploadDTO = new ImgUploadDTO();
        if (file.isEmpty()) {
            imgUploadDTO.setCode("0");
            return imgUploadDTO;
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String fileurl = imgPath+imgUrl+suffixName; // 上传后的路径
        File dest = new File(imgIp+fileurl);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgUploadDTO.setCode("1");
        imgUploadDTO.setImgUrl(fileurl);
        return imgUploadDTO;
    }

    @Override
    public String deleteImg(String imgUrl) {
        if (imgUrl==null || "".equals(imgUrl)) {
            return "no";
        }
        File file = new File(imgIp + imgUrl);
        if (!file.exists()) {
            return "no";
        }
        file.delete();
        return "ok";
    }
}
