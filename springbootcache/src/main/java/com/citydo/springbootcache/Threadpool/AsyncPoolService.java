package com.citydo.springbootcache.Threadpool;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * ①通过＠Async 注解表明该方法是个异步方法，如果注解在类级别，则表明该类所有的
 * *方法都是异步方法，而这里的方法自动被注入使用ThreadPoolTaskExecutor 作为TaskExecutor
 */

@Service
@Slf4j
public class AsyncPoolService {

    @Async
    public  void exectueAsyncThreadTask(int i){
        log.info("执行异步线程执行"+i);
    }

    @Async
    public void exectueAsyncThreadTaskPulses(int i){
        log.info("异步程序任务-"+(i + 1)+":"+i);
    }

}
