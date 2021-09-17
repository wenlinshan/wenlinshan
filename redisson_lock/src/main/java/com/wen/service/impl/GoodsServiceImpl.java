package com.wen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.domain.Goods;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.wen.mapper.GoodsMapper;
import com.wen.service.GoodsService;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    private RedissonClient redissonClient;

    public static final String LOCK_KEY = "lock";


    /**
     * 库存递减
     *
     * @param id  id
     * @param num 数量
     * @return
     */
    @Override
    public boolean killGoods(Long id, Integer num) {
        String key = LOCK_KEY + id;
        RLock lock = redissonClient.getLock(key);
        try {
            //上锁
            lock.lock();
            Goods goods = this.getById(id);
            if (goods.getQuantity()<=0){
                return false;
            }
            log.info("库存数量======"+goods.getQuantity());
            //将库存减操作
            goods.setQuantity(goods.getQuantity()-1);
            this.updateById(goods);

        } catch (Exception e) {
            return false;
        } finally {
            //解锁
            lock.unlock();
        }
        return true;
    }
}
