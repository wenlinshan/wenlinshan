package com.wen.service.impl;

import cn.vipwen.common.core.exception.BizException;
import cn.vipwen.common.lock.annotation.RLock;
import cn.vipwen.common.lock.util.LockUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.domain.Goods;
import com.wen.domain.Order;
import com.wen.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.wen.mapper.GoodsMapper;
import com.wen.service.GoodsService;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private OrderService orderService;


    public static final String LOCK_KEY = "'lock'+";


    /**
     * 库存递减
     *
     * @param id  id
     * @param num 数量
     * @return
     */
    @RLock(waitTime = 10, timeUnit = TimeUnit.SECONDS, key = LOCK_KEY + "#id")
    @Override
    public boolean killGoods(Long id, Integer num) {
        Goods goods = this.getById(id);
        if (goods.getQuantity() <= 0) {
            return false;
        }
        log.info("库存数量======" + goods.getQuantity());
        //将库存减操作
        goods.setQuantity(goods.getQuantity() - 1);
        this.updateById(goods);
        Order order = new Order();
        order.setGId(id);
        order.setCreateTime(new Date());
        orderService.save(order);
        return true;

    }
}
