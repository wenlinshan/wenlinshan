package com.example.zk_lock.controller;

import com.example.zk_lock.domain.User;
import com.example.zk_lock.send.TestSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RequestMapping("/xx")
@RestController
@Validated
public class TestController {
    @Autowired
    private TestSend testSend;

    @GetMapping("test")
    public String testController(User user) {
        return testSend.sendMessage(user);
    }

    @GetMapping("confirm")
    public String confirmTest(User user) {
        return testSend.sendConfirmMessage(user);
    }

    @GetMapping("/oo")
    public Long get(@Min(value = 10L, message = "id最小10") @RequestParam(value = "id") Long id) {
        return id;
    }

    @GetMapping("/user")
    public User getUser(@Validated User user) {
        return user;
    }
}
