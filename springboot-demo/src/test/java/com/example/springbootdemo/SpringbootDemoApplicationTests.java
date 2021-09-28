package com.example.springbootdemo;

import com.example.springbootdemo.task.AsyncTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class SpringbootDemoApplicationTests {
    @Autowired
    private AsyncTask asyncTasks;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
        //CompletableFuture<String> one = asyncTasks.doTaskOne();
        //CompletableFuture<String> two = asyncTasks.doTaskTwo();
        //CompletableFuture<String> three = asyncTasks.doTaskThree();
        asyncTasks.doTaskOne();
        asyncTasks.doTaskTwo();
        asyncTasks.doTaskThree();
        //CompletableFuture.allOf(one, two, three).join();
        //System.out.println(one.get() + ";" + two.get() + ";" + three.get());\
        //CompletableFuture.allOf().join()
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) + "毫秒");
    }

}
