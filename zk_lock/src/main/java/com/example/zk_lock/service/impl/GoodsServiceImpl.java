package com.example.zk_lock.service.impl;

import cn.vipwen.common.redis.annotation.RLock;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zk_lock.domain.Goods;
import com.example.zk_lock.mapper.GoodsMapper;
import com.example.zk_lock.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    RedissonClient redissonClient;


    /**
     * 库存递减
     *
     * @param id  id
     * @param num 数量
     * @return
     */
    @RLock(key = "#id",waitTime = 20)
    @Override
    public boolean killGoods(Long id, Integer num) throws InterruptedException {
        Goods goods = this.getById(id);
        if (goods.getQuantity() <= 0) {
            //库存数量不足,释放锁
            return false;
        }
        log.info("库存数量======" + goods.getQuantity());
        //将库存减操作
        goods.setQuantity(goods.getQuantity() - 1);
        this.updateById(goods);
        return true;

    }
}
