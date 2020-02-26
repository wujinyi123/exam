package com.system.exam.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel工具
 */
public class ExcelUtil {
    private static Map<String,Integer> cells = new HashMap<>();

    static {
        cells.put("teacher",6);
        cells.put("student",7);
        cells.put("clazz",4);
    }

    public static XSSFWorkbook exportExcel(String workbookName, List<String> listTitle, List<List<String>> datas) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(workbookName);
        XSSFRow row = null;
        XSSFCell cell = null;

        //表头
        row = sheet.createRow(0);
        //设置行高
        row.setHeightInPoints(18);
        //设置为居中加粗
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        for (int i=0; i<listTitle.size(); i++) {
            cell = row.createCell(i);
            cell.setCellValue(listTitle.get(i));
            cell.setCellStyle(style);
        }

        List<String> cellData = null;
        //表内容
        for (int i=0; i<datas.size(); i++) {
            row = sheet.createRow(i+1);
            //设置行高
            row.setHeightInPoints(15);
            cellData = datas.get(i);
            for (int j=0; j<cellData.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(cellData.get(j));
            }
        }
        FileOutputStream fos = new FileOutputStream(workbookName+".xlsx");
        workbook.write(fos);
        fos.flush();
        fos.close();
        return workbook;
    }

    /**
     * 处理上传的文件
     *
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static List getBankListByExcel(InputStream in, String fileName, String type) throws Exception {
        List<List<String>> list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null || sheet.getRow(0).getLastCellNum()!=cells.get(type)) {
                continue;
            }
            int cellLen = sheet.getRow(0).getLastCellNum();

            for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }

                List<String> li = new ArrayList<>();
                for (int y = 0; y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);

                    if (cell==null) li.add("");
                    else {
                        cell.setCellType(CellType.STRING);
                        li.add(cell.toString());
                    }
                }
                if (li.size()<cellLen) {
                    int k = cellLen - li.size();
                    for (int t=0; t<k; t++) li.add("");
                } else {
                    li = li.subList(0,cellLen);
                }
                list.add(li);
            }
        }
        work.close();
        return list;
    }

    /**
     * 判断文件格式
     *
     * @param inStr
     * @param fileName
     * @return
     * @throws Exception
     */
    private static Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("请上传excel文件！");
        }
        return workbook;
    }
}
