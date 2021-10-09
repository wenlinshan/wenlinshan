package com.wenlinshan.masterslavedemo.controller;

import com.wenlinshan.masterslavedemo.annotation.Master;
import com.wenlinshan.masterslavedemo.domain.Goods;
import com.wenlinshan.masterslavedemo.service.GoodsService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wls
 */
@RestController
public class GoodsController {
    @Resource
    private GoodsService goodsService;

    /**
     * 保存
     * @param goods 商品
     * @return  是否成功
     */
    @PostMapping("/saveGoods")
    public boolean saveGoods(Goods goods){
        return goodsService.saveGoods(goods);
    }

    /**
     * 删除
     *
     * @param id id
     * @return 是否成功
     */
    @DeleteMapping("/deleteGoods")
    public boolean deleteGoods(Long id) {
        return goodsService.deleteGoods(id);
    }

    /**
     * 查询全部
     *
     * @return 全部
     */
    @GetMapping("/getGoodsAll")
    public List<Goods> getGoodsAll() {
        return goodsService.getGoodsAll();
    }

    /**
     * 查询单个
     *
     * @param id id
     * @return 商品
     */
    @GetMapping("getGoodsById")
    public Goods getGoodsById(Long id) {
        return goodsService.getGoodsById(id);
    }
}
