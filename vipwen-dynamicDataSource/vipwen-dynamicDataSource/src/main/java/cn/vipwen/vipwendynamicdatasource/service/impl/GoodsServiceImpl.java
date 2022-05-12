package cn.vipwen.vipwendynamicdatasource.service.impl;

import cn.vipwen.vipwendynamicdatasource.annotation.DbSource;
import cn.vipwen.vipwendynamicdatasource.constant.DBConstantEnum;
import cn.vipwen.vipwendynamicdatasource.domain.Goods;
import cn.vipwen.vipwendynamicdatasource.mapper.GoodsMapper;
import cn.vipwen.vipwendynamicdatasource.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wls
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
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
    @DbSource(DBConstantEnum.SLAVE1)
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

    @Override
    public Goods getGoodsById(Long id) {
        return this.getById(id);
    }

}
