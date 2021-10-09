package com.wenlinshan.masterslavedemo.service;

import com.wenlinshan.masterslavedemo.domain.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author wls
 */
public interface GoodsService extends IService<Goods> {
    /**
     * 保存
     * @param goods 商品
     * @return  是否成功
     */
    boolean saveGoods(Goods goods);

    /**
     * 删除
     * @param id id
     * @return  是否成功
     */
    boolean deleteGoods(Long id);
    /**
     * 查询全部
     * @return  全部
     */
    List<Goods> getGoodsAll();

    /**
     * 查询单个
     * @param id id
     * @return  商品
     */
    Goods getGoodsById(Long id);

}
