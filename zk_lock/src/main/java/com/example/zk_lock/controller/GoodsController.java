package com.example.zk_lock.controller;

import com.example.zk_lock.service.GoodsService;
import com.example.zk_lock.util.DistributedLockUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author wenlinshan
 * @version 1.0
 * @date 2021/6/16 11:06
 * @desc
 */
@RequestMapping
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    @GetMapping("test")
    public String createOrderTest() {
        if (!goodsService.killGoods(1405065181720055809L, 1)) {
            return "库存不足";
        }
        return "创建订单成功";
    }

    @GetMapping("close")
    public String closeZk(){
        DistributedLockUtil.closeZkClient();
        return "关闭成功";
    }

}
