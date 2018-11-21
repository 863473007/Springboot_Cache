package com.citydo.springbootcache.Service;

import com.citydo.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class ExportService {

    public void exportReportForm(List<Department> list,String name, HttpServletResponse response) {
        //创建文件名字
        System.err.println("-------------"+name);
        File file = new File(name + ".xlsx");
        Workbook workbook = new XSSFWorkbook();
        //设置表的表样式
        CellStyle cellStyle = getColumnTopStyle(workbook);
        Sheet sheet = workbook.createSheet(name);
        int index = 0;
        Row row0 = sheet.createRow(index++);
        //设置第一行
        row0.createCell(0).setCellValue("主键");
        row0.createCell(1).setCellValue("姓名");
        //把查询结果放入到对应的列
        for (Department department : list) {
            Row row = sheet.createRow(index++);
            row.createCell(0).setCellValue(department.getId());
            row.createCell(1).setCellValue(department.getDepartmentName());
        }
        //设置每行字体居中效果
        for (int m = 0; m <= sheet.getLastRowNum(); m++) {
            Row rowStyle = sheet.getRow(m);
            for (int n = 0; n < rowStyle.getLastCellNum(); n++) {
                rowStyle.getCell(n).setCellStyle(cellStyle);
            }
        }
        response.reset();
        try {
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(file.getName().getBytes("utf-8"), "ISO8859-1") + "\"");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            workbook.write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (workbook != null) {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //设置样式
    public static CellStyle getColumnTopStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        //设置自动换行
        cellStyle.setWrapText(false);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return cellStyle;
    }


}
