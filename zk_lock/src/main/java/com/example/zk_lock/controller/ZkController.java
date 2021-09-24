package com.example.zk_lock.controller;

import com.example.zk_lock.util.DistributedLockUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class ZkController {


    @GetMapping("/lock")
    public String lock() throws InterruptedException {
        String lock = DistributedLockUtil.getLock();
        if (Objects.nonNull(lock)){
            //释放锁
            Thread.sleep(10000L);
            DistributedLockUtil.releaseLock(lock);
            return "获取锁成功！";
        }
        return "获取锁失败！";
    }
}
