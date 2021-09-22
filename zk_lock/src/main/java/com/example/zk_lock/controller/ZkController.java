package com.example.zk_lock.controller;

import com.example.zk_lock.util.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ZkController {
    @Autowired
    private DistributedLock distributedLock;

    @GetMapping("/lock")
    public String lock() throws InterruptedException {
        String lock = distributedLock.getLock();
        if (Objects.nonNull(lock)){
            //释放锁
            Thread.sleep(10000L);
            distributedLock.releaseLock(lock);
            return "获取锁成功！";
        }
        return "获取锁失败！";
    }
}
