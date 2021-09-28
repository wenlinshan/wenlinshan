package com.example.zk_lock.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zk_lock.domain.Goods;

/**
 * @author Administrator
 */
public interface GoodsService extends IService<Goods> {

    /**
     * 库存递减
     * @param id id
     * @param num 数量
     * @return
     */
    boolean killGoods(Long id,Integer num);


}
