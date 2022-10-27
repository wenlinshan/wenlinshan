package cn.vipwen.sharding.demo.controller;

import cn.vipwen.sharding.demo.domain.TOrder;
import cn.vipwen.sharding.demo.domain.TOrderItem;
import cn.vipwen.sharding.demo.service.TOrderItemService;
import cn.vipwen.sharding.demo.service.TOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author wls
 */
@AllArgsConstructor
@RequestMapping(value = "/test")
@RestController
public class TestController {
    private final TOrderService orderService;
    private final TOrderItemService orderItemService;
    @GetMapping
    public String testSave(){
        for (int i = 0; i < 1000; i++) {
            TOrder order = new TOrder();
            order.setOrderNo("A000" + i);
            order.setCreateName("订单 " + i);
            order.setPrice(new BigDecimal("" + i));
            orderService.save(order);

            TOrderItem orderItem = new TOrderItem();
            orderItem.setOrderId(order.getOrderId());
            orderItem.setOrderNo("A000" + i);
            orderItem.setItemName("服务项目" + i);
            orderItem.setPrice(new BigDecimal("" + i));
            orderItemService.save(orderItem);
        }
        return "ok";
    }

    @GetMapping(value = "getOrder")
    public List<TOrder> getOrder(@RequestParam(value = "id",required = false)Long id){
        return Objects.isNull(id)?orderService.list(): Collections.singletonList(orderService.getById(id));
    }
}
