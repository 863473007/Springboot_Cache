package com.citydo.springbootcache.controller;

import com.citydo.springbootcache.Service.EmployeeService;
import com.citydo.springbootcache.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmp(id);
        return  emp;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }


    @GetMapping("/deleteEmp")
    public String deleteEmp(Integer id){
        employeeService.deletEmp(id);
        return "success";
    }

    @GetMapping("/emp/lastname/{lastname}")
    public Employee getEmpByLastName(String lastName){
        return employeeService.getEmployeeLastName(lastName);
    }

}
