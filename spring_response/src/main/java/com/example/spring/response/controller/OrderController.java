package com.example.spring.response.controller;

import com.example.spring.response.constant.ResultData;
import com.example.spring.response.domain.Order;
import com.example.spring.response.exception.BizException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wls
 */
@RestController
public class OrderController {

    @GetMapping("/getHi")
    public ResultData<String> getHi(){
        int a = 1/0;
        return ResultData.success("hi");
    }

    @GetMapping("getWorld")
    public Order getWorld(){
        return new Order(1L,"购物车");
    }

    @GetMapping("/getError")
    public Order getError(){
        throw new BizException("失败了");
    }
}
