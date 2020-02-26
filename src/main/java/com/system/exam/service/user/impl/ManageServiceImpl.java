package com.system.exam.service.user.impl;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.dto.user.InsertDTO;
import com.system.exam.domain.qo.user.InsertErrorQO;
import com.system.exam.domain.qo.user.InsertQO;
import com.system.exam.domain.qo.user.IsNumberQO;
import com.system.exam.mapper.user.ManageMapper;
import com.system.exam.service.user.ManageService;
import com.system.exam.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
     * 导出学院代码、班级号
     * @param response
     */
    public void instructions(HttpServletResponse response) {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("学院");
        listTitle.add("代码");
        listTitle.add("");
        listTitle.add("专业");
        listTitle.add("班级号");

        List<List<String>> datas = new ArrayList<>();
        //所有学院
        List<MbDTO> listCollege = manageMapper.listCollege();
        //所有班级
        List<MbDTO> listClazz = manageMapper.listClazz();
        List<String> dataList = null;
        for (int i=0; i<listClazz.size(); i++) {
            dataList = new ArrayList<>();
            if (i<listCollege.size()) {
                dataList.add(listCollege.get(i).getName());
                dataList.add(listCollege.get(i).getCode());
            } else {
                dataList.add("");
                dataList.add("");
            }
            dataList.add("");
            dataList.add(listClazz.get(i).getName());
            dataList.add(listClazz.get(i).getCode());
            datas.add(dataList);
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("学院代码、班级号.xlsx", "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            XSSFWorkbook workbook = ExcelUtil.exportExcel("学院代码、班级号",listTitle,datas);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入错误
     * @param insertErrorQO
     * @param response
     */
    public void insertError(InsertErrorQO insertErrorQO, HttpServletResponse response) {
        String fileName = "";
        List<String> listTitle = new ArrayList<>();
        if ("teacher".equals(insertErrorQO.getType())) {
            listTitle.add("教师号");
            fileName = "错误教师信息";
        } else {
            listTitle.add("学号");
            fileName = "错误学生信息";
        }
        listTitle.add("姓名");
        listTitle.add("性别");
        listTitle.add("电话");
        listTitle.add("邮箱");
        listTitle.add("学院代码");
        if ("student".equals(insertErrorQO.getType())) {
            listTitle.add("班级号");
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName+".xlsx", "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(fileName,listTitle,insertErrorQO.getDataList());
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 导入信息
     * @param file
     * @param insertQO
     * @return
     */
    @Override
    public InsertDTO insertUser(MultipartFile file, InsertQO insertQO) {
        InsertDTO insertDTO = new InsertDTO();
        //判断是否上传了文件
        if (file.isEmpty()) {
            insertDTO.setMsg("请上传文件");
            return insertDTO;
        }
        //读取文件内容
        InputStream inputStream = null;
        List<List<String>> list = null;
        try {
            inputStream = file.getInputStream();
            list = ExcelUtil.getBankListByExcel(inputStream, file.getOriginalFilename(), insertQO.getType());
            inputStream.close();
        } catch (Exception e) {
            insertDTO.setMsg("文件获取失败："+e);
            return insertDTO;
        }
        if (list.size()==0) {
            insertDTO.setMsg("文件无数据");
            return insertDTO;
        }

        if (!"teacher".equals(insertQO.getType()) && !"student".equals(insertQO.getType()) && !"clazz".equals(insertQO.getType())) {
            insertDTO.setMsg("上传失败");
            return insertDTO;
        }

        //所有学院
        List<MbDTO> listCollege = manageMapper.listCollege();
        //所有班级
        List<MbDTO> listClazz = manageMapper.listClazz();

        insertDTO.setDataList(new ArrayList<>());
        int successSum = 0;
        int flag = 0;
        String dataMsg = "";
        List<String> dataList = null;
        for (int i=0; i<list.size(); i++) {
            dataMsg = "";
            dataList = list.get(i);
            insertQO.setDataList(dataList);
            if ("clazz".equals(insertQO.getType())) {
                dataMsg = checkData(listCollege, dataList.get(0), "学院代码错误 ");
                if (dataList.get(1).length()!=4 || !isNumeric(dataList.get(1))) {
                    dataMsg += "年级不是四位整数 ";
                }
                if ("".equals(dataMsg) && (dataList.get(2).length()!=dataList.get(0).length()+dataList.get(1).length()+2
                    || !(dataList.get(0)+dataList.get(1)).equals(dataList.get(2).substring(0,dataList.get(2).length()-2)))) {
                    dataMsg += "班级号命名错误 ";
                }
                if (manageMapper.isNumber(new IsNumberQO(insertQO.getType(), dataList.get(2))) != 0) {
                    dataMsg += "班级号已存在 ";
                }
            } else {
                dataMsg = checkData(listCollege, dataList.get(5), "学院代码错误 ");
                if ("student".equals(insertQO.getType())) {
                    dataMsg += checkData(listClazz, dataList.get(6), "班级错误 ");
                }
                if (!"男".equals(list.get(i).get(2)) && !"女".equals(dataList.get(2))) {
                    dataMsg += "性别错误 ";
                }
                if (manageMapper.isNumber(new IsNumberQO(insertQO.getType(), dataList.get(0))) != 0) {
                    dataMsg += "账号已存在 ";
                }
            }
            if (!"".equals(dataMsg)) {
                dataList.add(dataMsg);
                insertDTO.getDataList().add(dataList);
            } else {
                dataList.add("1");
                //flag = manageMapper.insertUser(insertUserQO);
                //flag = 1;
                if (flag!=0) {
                    successSum++;
                } else {
                    dataList.remove(dataList.size()-1);
                    dataList.add("上传错误");
                    insertDTO.getDataList().add(dataList);
                }
            }
        }

        insertDTO.setType(insertQO.getType());
        insertDTO.setSuccess(successSum+"");
        insertDTO.setFail(list.size()-successSum+"");
        insertDTO.setMsg("ok");
        return insertDTO;
    }

    /**
     * 判断是否是整数
     * @param str
     * @return
     */
    private boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 验证代码是否正确
     * @param mbDTOList
     * @param code
     * @return
     */
    private String checkData(List<MbDTO> mbDTOList, String code, String codeMsg) {
        String dataMsg = "";
        if (code==null || "".equals(code)) {
            return codeMsg;
        }
        int flag = 0;
        for (MbDTO mbDTO:mbDTOList) {
            if (code.equals(mbDTO.getCode())) {
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
