package com.citydo.springbootcache.Threadpool;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Author: 王俊超
 * Date: 2017-07-11 07:55
 * All Rights Reserved !!!
 */
public class MainPool {


    public static  void main(String[] args){
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(ThreadPoolConfig.class);
        AsyncPoolService AsyncService = annotationConfigApplicationContext.getBean(AsyncPoolService.class);
        for (int i = 0; i < 100000; i++) {
            AsyncService.exectueAsyncThreadTask(i);//开始启动线程
            AsyncService.exectueAsyncThreadTaskPulses(i);
        }
        annotationConfigApplicationContext.close();//关闭线程
    }
}

