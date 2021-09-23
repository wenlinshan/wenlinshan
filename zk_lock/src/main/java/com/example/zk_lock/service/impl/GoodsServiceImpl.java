package com.example.zk_lock.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zk_lock.domain.Goods;
import com.example.zk_lock.mapper.GoodsMapper;
import com.example.zk_lock.service.GoodsService;
import com.example.zk_lock.util.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Administrator
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    private DistributedLock distributedLock;


    /**
     * 库存递减
     *
     * @param id  id
     * @param num 数量
     * @return
     */
    @Override
    public boolean killGoods(Long id, Integer num) {

        String lock = distributedLock.getLock();
        if (Objects.nonNull(lock)) {
            Goods goods = this.getById(id);
            if (goods.getQuantity() <= 0) {
                distributedLock.releaseLock(lock);
                return false;
            }
            log.info("库存数量======" + goods.getQuantity());
            //将库存减操作
            goods.setQuantity(goods.getQuantity() - 1);
            this.updateById(goods);
            distributedLock.releaseLock(lock);
            return true;
        }
        return false;
    }
}
