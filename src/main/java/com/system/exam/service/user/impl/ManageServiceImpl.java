package com.system.exam.service.user.impl;

import com.system.exam.domain.dto.user.InsertUserDTO;
import com.system.exam.domain.qo.user.InsertUserQO;
import com.system.exam.domain.qo.user.IsUserQO;
import com.system.exam.mapper.user.ManageMapper;
import com.system.exam.service.user.ManageService;
import com.system.exam.util.ExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 管理业务层实现类
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private ManageMapper manageMapper;

    /**
     * 导入用户信息
     * @param file
     * @param insertUserQO
     * @return
     */
    @Override
    public InsertUserDTO insertUser(MultipartFile file, InsertUserQO insertUserQO) {
        InsertUserDTO insertUserDTO = new InsertUserDTO();
        //判断是否上传了文件
        if (file.isEmpty()) {
            insertUserDTO.setMsg("请上传文件");
            return insertUserDTO;
        }
        //读取文件内容
        InputStream inputStream = null;
        List<List<String>> list = null;
        try {
            inputStream = file.getInputStream();
            list = ExcelUtil.getBankListByExcel(inputStream, file.getOriginalFilename(), insertUserQO.getType());
            inputStream.close();
        } catch (Exception e) {
            insertUserDTO.setMsg("文件获取失败："+e);
            return insertUserDTO;
        }
        if (list.size()==0) {
            insertUserDTO.setMsg("文件无数据");
            return insertUserDTO;
        }

        if (!"teacher".equals(insertUserQO.getType()) && !"student".equals(insertUserQO.getType())) {
            insertUserDTO.setMsg("上传失败");
            return insertUserDTO;
        }

        //所有学院代码
        List<String> listCollegeCode = manageMapper.listCollegeCode();
        //所有班级代码
        List<String> listClazzNumber = manageMapper.listClazzNumber();

        insertUserDTO.setDataList(list);
        insertUserDTO.setDataMsg(new ArrayList<>());
        insertUserDTO.setFlag(new ArrayList<>());
        int successSum = 0;
        int flag = 0;
        String dataMsg = "";
        List<String> dataList = null;
        for (int i=0; i<list.size(); i++) {
            dataMsg = "";
            dataList = list.get(i);
            insertUserQO.setDataList(dataList);
            dataMsg = checkData(listCollegeCode,dataList.get(5),"学院代码错误 ");
            if ("student".equals(insertUserQO.getType())) {
                dataMsg += checkData(listCollegeCode,dataList.get(6),"班级错误 ");
            }
            if (!"男".equals(list.get(i).get(2)) && !"女".equals(dataList.get(2))) {
                dataMsg += "性别错误 ";
            }
            if (manageMapper.isNumber(new IsUserQO(insertUserQO.getType(),dataList.get(0)))!=0) {
                dataMsg += "学号或教师号错误 ";
            }
            if (!"".equals(dataMsg)) {
                insertUserDTO.getDataMsg().add(dataMsg);
                insertUserDTO.getFlag().add("0");
            } else {
                //flag = manageMapper.insertUser(insertUserQO);
                if (flag!=0) {
                    successSum++;
                    insertUserDTO.getDataMsg().add("");
                } else {
                    insertUserDTO.getDataMsg().add("上传错误");
                }
                insertUserDTO.getFlag().add(flag+"");
            }
        }

        insertUserDTO.setSuccess(successSum+"");
        insertUserDTO.setFail(list.size()-successSum+"");
        insertUserDTO.setMsg("ok");
        return insertUserDTO;
    }

    /**
     * 验证代码是否正确
     * @param listCollegeCode
     * @param code
     * @return
     */
    private String checkData(List<String> listCollegeCode, String code, String codeMsg) {
        String dataMsg = "";
        if (code==null || "".equals(code)) {
            return codeMsg;
        }
        int flag = 0;
        for (String collegeCode:listCollegeCode) {
            if (code.equals(collegeCode)) {
                flag = 1;
                break;
            }
        }
        if (flag==0) {
            return codeMsg;
        } else {
            return dataMsg;
        }
    }
}
