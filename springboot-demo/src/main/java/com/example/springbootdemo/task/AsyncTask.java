package com.example.springbootdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * @author Administrator
 */
@Component
@Slf4j
public class AsyncTask {

    public static Random random = new Random();

    @Async("pool-1")
    public CompletableFuture<String> doTaskOne() throws Exception {
        log.info("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("完成任务一，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务一完成; 线程号："+Thread.currentThread().getName());
    }

    @Async("pool-1")
    public CompletableFuture<String> doTaskTwo() throws Exception {
        log.info("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("完成任务二，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务二完成; 线程号："+Thread.currentThread().getName());
    }

    @Async("pool-2")
    public CompletableFuture<String> doTaskThree() throws Exception {
        log.info("开始做任务三");
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("完成任务三，耗时：" + (end - start) + "毫秒");
        return CompletableFuture.completedFuture("任务三完成; 线程号："+Thread.currentThread().getName());
    }
}
