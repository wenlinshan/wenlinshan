package com.wenlinshan.masterslavedemo.service.impl;

import com.wenlinshan.masterslavedemo.annotation.Master;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wenlinshan.masterslavedemo.domain.Goods;
import com.wenlinshan.masterslavedemo.mapper.GoodsMapper;
import com.wenlinshan.masterslavedemo.service.GoodsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wls
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{
    /**
     * 保存
     * @param goods 商品
     * @return  是否成功
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveGoods(Goods goods){
       return this.save(goods);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteGoods(Long id) {
        return this.removeById(id);
    }

    /**
     * 查询全部
     *
     * @return 全部
     */
    @Override
    public List<Goods> getGoodsAll() {
        return this.list();
    }

    /**
     * 查询单个
     *
     * @param id id
     * @return 商品
     */
    @Master
    @Override
    public Goods getGoodsById(Long id) {
        return this.getById(id);
    }

}
