package com.system.exam.service.user.impl;

import com.system.exam.domain.dto.common.MbDTO;
import com.system.exam.domain.dto.user.*;
import com.system.exam.domain.qo.user.*;
import com.system.exam.mapper.user.ManageMapper;
import com.system.exam.service.user.ManageService;
import com.system.exam.util.ExcelUtil;
import com.system.exam.util.Md5Util;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 管理业务层实现类
 */
@Service
public class ManageServiceImpl implements ManageService {
    @Resource
    private ManageMapper manageMapper;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 全校情况
     * @return
     */
    @Override
    public SchoolInfoDTO getSchoolInfo() {
        return manageMapper.getSchoolInfo();
    }

    /**
     * 分页学院
     * @return
     */
    @Override
    public List<PageCollegeDTO> pageCollege() {
        return manageMapper.pageCollege();
    }

    /**
     * 分页查询班级
     * @param pageClazzQO
     * @return
     */
    @Override
    public List<PageClazzDTO> pageClazz(PageClazzQO pageClazzQO) {
        return manageMapper.pageClazz(pageClazzQO);
    }

    /**
     * 分页查询教师
     * @param pageUserQO
     * @return
     */
    @Override
    public List<PageTeacherDTO> pageTeacher(PageUserQO pageUserQO) {
        return manageMapper.pageTeacher(pageUserQO);
    }

    /**
     * 分页查询学生
     * @param pageUserQO
     * @return
     */
    @Override
    public List<PageStudentDTO> pageStudent(PageUserQO pageUserQO) {
        return manageMapper.pageStudent(pageUserQO);
    }

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
        exportExcel(response,"学院代码、班级号",listTitle,datas);
    }

    /**
     * 导入错误
     * @param insertErrorQO
     * @param response
     */
    public void insertError(InsertErrorQO insertErrorQO, HttpServletResponse response) {
        String fileName = "";
        List<String> errorData = (List<String>) redisTemplate.opsForValue().get(insertErrorQO.getUuid());
        insertErrorQO.setDataList(getErrorDataList(errorData));
        List<String> listTitle = new ArrayList<>();
        if ("clazz".equals(insertErrorQO.getType())) {
            fileName = "错误班级信息";
            listTitle.add("学院代码");
            listTitle.add("年级（四位整数）");
            listTitle.add("班级号（学院代码+年级+XX）");
            listTitle.add("专业");
        } else {
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
        }
        exportExcel(response,fileName,listTitle,insertErrorQO.getDataList());
    }

    /**
     * 新一届班级
     * @param response
     */
    @Override
    public void newClazz(HttpServletResponse response) {
        List<String> listTitle = new ArrayList<>();
        listTitle.add("学院代码");
        listTitle.add("年级（四位整数）");
        listTitle.add("班级号（学院代码+年级+XX）");
        listTitle.add("专业");

        List<ClazzDTO> newClazzList = manageMapper.newClazz();
        List<List<String>> datas = new ArrayList<>();

        for (ClazzDTO dto:newClazzList) {
            datas.add(dtoToList(dto));
        }
        exportExcel(response,"新一届班级",listTitle,datas);
    }

    /**
     * 新一届学生学号
     * @param list
     * @return
     */
    @Override
    public String newStuNumber(List<NewStuNumberQO> list) {
        int num = 1;
        String year = manageMapper.newGrade();
        List<String> uuidList = new ArrayList<>();
        String uuid = null;
        List<String> dataList = null;

        StringBuffer dat = null;
        Map<String,Object> map = null;
        for (NewStuNumberQO dto:list) {
            dataList = new ArrayList<>();
            int boy = Integer.parseInt(dto.getBoy());
            int girl = Integer.parseInt(dto.getGirl());
            for (int i=0; i<boy; i++) {
                dat = new StringBuffer(dto.getCollegeCode());
                dat.append("!@#"+year+"01"+String.format("%05d", num));
                dat.append("!@#M");
                dataList.add(dat.toString());
                num++;
            }
            for (int i=0; i<girl; i++) {
                dat = new StringBuffer(dto.getCollegeCode());
                dat.append("!@#"+year+"02"+String.format("%05d", num));
                dat.append("!@#G");
                dataList.add(dat.toString());
                num++;
            }
            map = new HashMap<>();
            map.put("name",dto.getCollegeName());
            map.put("data",dataList);
            uuid = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid,map,2,TimeUnit.MINUTES);
            uuidList.add(uuid);
        }

        uuid = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(uuid,uuidList,2,TimeUnit.MINUTES);
        return uuid;
    }

    /**
     * 新一届学生学号
     * @param response
     * @param uuid
     */
    @Override
    public void newStuNum(HttpServletResponse response,String uuid) {
        List<String> uuidList = (List<String>) redisTemplate.opsForValue().get(uuid);

        List<String> listTitle = new ArrayList<>();
        listTitle.add("学号");
        listTitle.add("姓名");
        listTitle.add("性别");
        listTitle.add("电话");
        listTitle.add("邮箱");
        listTitle.add("学院代码");
        listTitle.add("班级号");

        List<Map<String,Object>> datas = new ArrayList<>();
        Map<String,Object> map = null;
        List<String> list = null;
        List<String> dataList = null;
        String[] arr = null;
        List<List<String>> mapData = null;
        for (String key:uuidList) {
            map = (Map<String, Object>) redisTemplate.opsForValue().get(key);
            list = (List<String>) map.get("data");
            mapData = new ArrayList<>();
            for (String dat:list) {
                arr = dat.split("!@#");
                dataList = new ArrayList<>();
                dataList.add(arr[1]);
                dataList.add("");
                dataList.add(arr[2]);
                dataList.add("");
                dataList.add("");
                dataList.add(arr[0]);
                dataList.add("");
                mapData.add(dataList);
            }
            map.put("dataList",mapData);
            datas.add(map);
        }

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode("新一届学生"+".xlsx", "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(listTitle,datas);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 毕业操作
     * @param graduatedQO
     * @return
     */
    @Override
    public String graduated(GraduatedQO graduatedQO) {
        String result = "";
        int clazzs = manageMapper.graduated(graduatedQO);
        int students = manageMapper.graduatedStudent();
        result = "毕业班级"+clazzs+"个，毕业生"+students+"人";
        if (clazzs==0 || students==0) {
            result = "";
        }
        return result;
    }

    private List<String> dtoToList(ClazzDTO dto) {
        List list = new ArrayList<>();
        list.add(dto.getCollegeCode());
        list.add(dto.getYear());
        list.add(dto.getClazz());
        list.add(dto.getMajor());
        return list;
    }

    private List<List<String>> getErrorDataList(List<String> errorData) {
        List<List<String>> datas = new ArrayList<>();
        List<String> list = null;
        for (String str:errorData) {
            list = new ArrayList<>();
            for (String s:str.split("!@#")) {
                list.add(s);
            }
            datas.add(list);
        }
        return datas;
    }

    /**
     * 导出数据
     * @param response
     * @param fileName
     * @param listTitle
     * @param datas
     */
    private void exportExcel(HttpServletResponse response,String fileName,List<String> listTitle,List<List<String>> datas) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName+".xlsx", "utf-8"));
            OutputStream outputStream = response.getOutputStream();
            XSSFWorkbook workbook = ExcelUtil.exportExcel(fileName,listTitle,datas);
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
    public InsertDTO insert(MultipartFile file, InsertQO insertQO) {
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
            if ("clazz".equals(insertQO.getType())) {
                dataMsg = checkData(listCollege, dataList.get(0), "学院代码错误 ");
                if ("".equals(dataList.get(0)) || "".equals(dataList.get(1))
                        || "".equals(dataList.get(2)) || "".equals(dataList.get(3))) {
                    dataMsg += "所有不能为空";
                }
                if (dataList.get(1).length()!=4 || !isNumeric(dataList.get(1))) {
                    dataMsg += "年级不是四位整数 ";
                }
                if ((dataList.get(2).length()!=dataList.get(0).length()+dataList.get(1).length()+2
                    || !(dataList.get(0)+dataList.get(1)).equals(dataList.get(2).substring(0,dataList.get(2).length()-2)))) {
                    dataMsg += "班级号命名错误 ";
                }
                if (manageMapper.isNumber(new IsNumberQO(insertQO.getType(), dataList.get(2))) != 0) {
                    dataMsg += "班级号已存在 ";
                }
            } else {
                dataMsg = checkData(listCollege, dataList.get(5), "学院代码错误 ");
                if ("student".equals(insertQO.getType())
                        && ("".equals(dataList.get(0)) || "".equals(dataList.get(1))
                        || "".equals(dataList.get(2)) || "".equals(dataList.get(5))
                        || "".equals(dataList.get(6)))) {
                    dataMsg += "除电话邮箱外其余不能为空";
                }
                if ("teacher".equals(insertQO.getType())
                        && ("".equals(dataList.get(0)) || "".equals(dataList.get(1))
                        || "".equals(dataList.get(2)) || "".equals(dataList.get(5)))) {
                    dataMsg += "除电话邮箱外其余不能为空";
                }
                if ("student".equals(insertQO.getType())) {
                    dataMsg += checkData(listClazz, dataList.get(6), "班级错误 ");
                }
                if (!"M".equals(list.get(i).get(2)) && !"G".equals(dataList.get(2))) {
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
                makeList(insertQO,dataList);
                if ("0".equals(insertQO.getFlag())) {
                    flag = 0;
                } else {
                    flag = manageMapper.insert(insertQO);
                }
                if (flag!=0) {
                    successSum++;
                } else {
                    dataList.add("上传错误");
                    insertDTO.getDataList().add(dataList);
                }
            }
        }

        insertDTO.setType(insertQO.getType());
        insertDTO.setSuccess(successSum+"");
        insertDTO.setFail(list.size()-successSum+"");
        //insertDTO.setFail("0");
        insertDTO.setMsg("ok");
        String uuid = UUID.randomUUID().toString();
        insertDTO.setUuid(uuid);
        redisTemplate.opsForValue().set(uuid, getErrorData(insertDTO.getDataList()), 30, TimeUnit.MINUTES);
        return insertDTO;
    }

    private void makeList(InsertQO insertQO,List<String> dataList) {
        insertQO.setFlag("1");
        switch (insertQO.getType()) {
            case "clazz":{
                insertQO.setCollegeCode(dataList.get(0));
                insertQO.setYear(dataList.get(1));
                insertQO.setClazz(dataList.get(2));
                insertQO.setMajor(dataList.get(3));
                break;
            }
            case "student":{
                insertQO.setNumber(dataList.get(0));
                insertQO.setName(dataList.get(1));
                insertQO.setSex(dataList.get(2));
                insertQO.setTel(dataList.get(3));
                insertQO.setEmail(dataList.get(4));
                insertQO.setClazz(dataList.get(6));
                insertQO.setPassword(Md5Util.md5("123456"));
                break;
            }
            case "teacher":{
                insertQO.setNumber(dataList.get(0));
                insertQO.setName(dataList.get(1));
                insertQO.setSex(dataList.get(2));
                insertQO.setTel(dataList.get(3));
                insertQO.setEmail(dataList.get(4));
                insertQO.setCollegeCode(dataList.get(5));
                insertQO.setPassword(Md5Util.md5("123456"));
                break;
            }
            default:{
                insertQO.setFlag("0");
                break;
            }
        }
    }

    private List<String> getErrorData(List<List<String>> dataList) {
        List<String> errorList = new ArrayList<>();

        StringBuffer dataStr = null;
        for (List<String> datas:dataList) {
            dataStr = new StringBuffer(datas.get(0));
            for (int i=1; i<datas.size(); i++) {
                dataStr.append("!@#"+datas.get(i));
            }
            errorList.add(dataStr.toString());
        }
        return errorList;
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
