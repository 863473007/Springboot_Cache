package com.citydo.springbootcache.controller;

import com.citydo.springbootcache.Service.DeptService;
import com.citydo.springbootcache.Service.ExportService;
import com.citydo.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ExportController {

    @Autowired
    DeptService deptService;

    @Autowired
    ExportService exportService;

    @GetMapping("/export/{name}")
    public void exportReportForm(@PathVariable("name")String name, HttpServletResponse response) {
        List<Department> list = deptService.getDeptByIdLists();
        //获取打印数据
        try {
            System.err.println("========="+name);
            exportService.exportReportForm(list,name,response);
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("导入数据失败");
        }

    }
}
