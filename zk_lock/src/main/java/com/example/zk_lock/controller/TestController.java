package com.example.zk_lock.controller;

import com.example.zk_lock.domain.User;
import com.example.zk_lock.send.TestSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private TestSend testSend;

    @GetMapping("test")
    public String testController(User user){
        return testSend.sendMessage(user);
    }
}
