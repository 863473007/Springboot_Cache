package com.citydo.springbootcache.Service;

import com.citydo.springbootcache.bean.Employee;
import com.citydo.springbootcache.mapper.EmployeeMapper;
import javafx.scene.chart.ValueAxis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;


@CacheConfig(cacheNames = "emp")//抽取缓存的公共配置
@Service
public class EmployeeService {

    /**
     *     @Cacheable(cacheNames = "emp",condition = "#id>0")//指定符合条件的情况才放缓存
     *     @Cacheable(cacheNames = "emp",condition = "#id>0",unless = "#result==null") //否定缓存  当nuless指定的条件为true 方法的返回值就不糊被缓存 可以获取到结构进行判断
     *     @Cacheable(cacheNames = "emp",key = "#root.args[0]")//将运行的方法缓存 以后再要相同的数据，直接从缓存获取
     *     @Cacheable(cacheNames = {"emp","emp"},key = )
     *     @Cacheable(cacheNames = {"emp","emp"},sync) 是否使用异步模式
     */

    @Autowired
    EmployeeMapper employeeMapper;

    //获取缓存数据
    @Cacheable(/*cacheNames = "emp"*/)
    public Employee getEmp(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }

    //拼接缓存key
    @Cacheable(cacheNames = {"emp"},key ="#root.methodName+'['+#id+']'")
    public Employee getEmpKey(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }
    //指定使用哪个缓存管理器与缓存方法
    //@Cacheable(cacheNames = {"emp"}, keyGenerator="myKeyGenerator",cacheManager = "")
    public Employee getEmpKeyGenerator(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }
    //判断缓存条件 第一个参数值大于1缓存
    @Cacheable(/*cacheNames = "emp",*/condition = "#id>1 and #root.methodName eq 'AAA'")
    public Employee getEmpCondition(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }
    //如果一个参数的值是2 结果不缓存
    @Cacheable(/*cacheNames = "emp",*/unless = "#a0==2")
    public Employee getEmpUnless(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }
    //异步unless不支持
    @Cacheable(/*cacheNames = "emp",*/sync = true)
    public Employee getEmpSync(Integer id){
        System.out.println("查询"+id);
        Employee empById = employeeMapper.getEmpById(id);
        return  empById;
    }

    //修改数据库的某一个值的数据 ，同时更新缓存
    //运行时机
    //先调用目标方法  将目标方法的结果的缓存起来
    @CachePut(/*value = "emp"*/)
    public Employee updateEmp(Employee employee){
        employeeMapper.updateEmp(employee);
        return  employee;
    }

    //缓存清除
    //通过key清除指定的数据
    //@CacheEvict(value = "emp")
    //@CacheEvict(value = "emp",allEntries = true)//清除所有缓存
    @CacheEvict(/*value = "emp",*/beforeInvocation = false)//默认在方法之后清理缓存 如果出现异常缓存不会清除
    public void deletEmp(Integer id){
        employeeMapper.deletEmpById(1);
    }


    //定义复杂的缓存功能
    @Caching(
            cacheable = {
                    @Cacheable(/*value = "emp",*/key="#lastName")
            },
            put = {
                    @CachePut(/*value="emp",*/key = "#result.id"),
                    @CachePut(/*value = "emp",*/key = "result.email")
            }
    )
    public  Employee getEmployeeLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
