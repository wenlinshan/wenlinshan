package com.wen;

import com.wen.domain.Goods;
import com.wen.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author wenlinshan
 * @version 1.0
 * @date 2021/6/16 14:13
 * @desc
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTest {
    @Resource
    private GoodsService goodsService;

    @Test
    public void testSave(){
       goodsService.save(Goods.builder().name("华为").quantity(100).build());
    }
}
