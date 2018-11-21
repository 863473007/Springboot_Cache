package com.citydo.springbootcache.controller;

import com.citydo.springbootcache.Service.DeptService;
import com.citydo.springbootcache.bean.Department;
import com.citydo.springbootcache.utils.ObjectUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class DeptController {

      @Autowired
      DeptService deptService;

      //操作k-v都是字符串的
      @Autowired
      StringRedisTemplate stringRedisTemplate;

      //k-v都是对象的
      @Autowired
      RedisTemplate redisTemplate;

      //将数据库的数据放到redis中，失效为一小时
      @GetMapping("/list")
      public Department getDeptlist(){
            //或者list
            String list = stringRedisTemplate.opsForValue().get("1");
            //将list转化对象
            Gson gson=new Gson();
            Department department=gson.fromJson(list,Department.class);
            //判断缓存是否有值
            if(ObjectUtils.isEmpty(department)){
                  return deptService.getDeptByIdList();

            }else{
                  return department;
            }
      }
}
