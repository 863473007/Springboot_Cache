package com.citydo.springbootcache.mapper;

import com.citydo.springbootcache.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {

    @Select("SELECT * FROM department WHERE id=#{id}")
    Department getDeptById(Integer id);

    @Select("SELECT * FROM department")
    Department getDeptByIdList();


    @Select("SELECT * FROM department")
    List<Department> getDeptByIdLists();

}
