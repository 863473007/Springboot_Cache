package com.citydo.springbootcache.Threadpool;


import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
/**
 * ①利用＠EnableAsync 注解开启异步任务支持。
 * ②配置类实现AsyncConfigurer 接口并重写getAsyncExecutor 方法，并返回一个
 * ThreadPoolTaskExecutor ，这样我们就获得了一个基于线程池TaskExecutor。
 * Author: 王俊超
 * Date: 2017-07-11 07:57
 * All Rights Reserved !!!
 */

@Configuration
@EnableAsync//开启异步线程
//解决No qualifying bean of type 'com.citydo.springbootcache.Service.AsyncTaskService' available
@ComponentScan("com.citydo.springbootcache.Threadpool") //扫描此下的线程服务
public class ThreadPoolConfig implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);//核心线程数
        threadPoolTaskExecutor.setMaxPoolSize(500);//最大线程数
        threadPoolTaskExecutor.setQueueCapacity(2500);//最大队列数
        threadPoolTaskExecutor.setKeepAliveSeconds(3000);//超过空闲的线程数进行回收
        threadPoolTaskExecutor.initialize();//初始化线程池
        return threadPoolTaskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler(){
        return new SimpleAsyncUncaughtExceptionHandler();//开启定时任务进行 异步线程
    }

}
