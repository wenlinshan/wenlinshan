package com.example.zk_lock.controller;

import com.example.zk_lock.domain.User;
import com.example.zk_lock.send.TestSend;
import com.example.zk_lock.send.TtlSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RequestMapping("/xx")
@RestController
@Validated
public class TtlController {
    @Autowired
    private TtlSend ttlSend;

    @GetMapping("ttl")
    public String testController(User user) {
        return ttlSend.sendTtlMessage(user);
    }

    @PostMapping("plugin")
    public String pluginController(User user) {
        return ttlSend.pluginMessage(user);
    }
}
