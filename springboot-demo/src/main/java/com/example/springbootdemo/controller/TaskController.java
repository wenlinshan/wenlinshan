package com.example.springbootdemo.controller;

import com.example.springbootdemo.task.AsyncTask;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

@RestController
public class TaskController {

    @Qualifier("pool-1")
    @Autowired
    Executor poolExecutor;

    @Autowired
    private  AsyncTask asyncTasks;


    @GetMapping("asyncTask")
    public String asyncTask() throws Exception {
        asyncTasks.doTaskOne();
        asyncTasks.doTaskTwo();
        asyncTasks.doTaskThree();
        return "ok!";
    }

    /**
     * 有返回值的异步编程
     * @return
     * @throws Exception
     */
    @GetMapping("asyncTask2")
    public String asyncTask2() throws Exception {
        long start = System.currentTimeMillis();
        CompletableFuture<String> a1 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    return asyncTasks.doTaskOne();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        },poolExecutor);
        CompletableFuture<String> a2 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    return asyncTasks.doTaskTwo();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        },poolExecutor);
        CompletableFuture<String> a3 = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {

                try {
                    return asyncTasks.doTaskThree();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        },poolExecutor);
        CompletableFuture.allOf(a1,a2,a3).join();
        System.out.println("========"+a1.get()+"================"+a2.get()+"=========="+a3.get());
        long end = System.currentTimeMillis();
        System.out.println("总耗时："+(end- start));
        return "ok!";
    }
}
