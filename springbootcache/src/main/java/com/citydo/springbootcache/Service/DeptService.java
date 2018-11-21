package com.citydo.springbootcache.Service;


import com.citydo.springbootcache.bean.Department;
import com.citydo.springbootcache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {

   @Autowired
   DepartmentMapper departmentMapper;



   @Autowired
   RedisTemplate<Object, Department> deptRedisTemplate;




   public Department getDeptById(Integer id){
      Department deptById = departmentMapper.getDeptById(id);
      return  deptById;
   }



   public Department getDeptByIdList(){
      Department deptByIdList = departmentMapper.getDeptByIdList();
      // 存入缓存
      //(1)自己将对象转为json
      //(2)redisTemplate默认的序列化规则；改变默认的序列化规则；
      deptRedisTemplate.opsForValue().set(1,deptByIdList);
      return  deptByIdList;
   }


   public List<Department> getDeptByIdLists(){
      return  departmentMapper.getDeptByIdLists();
   }

}
