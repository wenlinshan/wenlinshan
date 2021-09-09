package com.wen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wen.domain.Goods;
import com.wen.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testSave(){
       goodsService.save(Goods.builder().name("华为").quantity(100).build());
    }

    @Test
    public void testSet(){
        Goods goods = Goods.builder().name("华为").quantity(1000).build();
        Map<String,Object> map = JSONObject.parseObject(JSON.toJSONString(goods));
        stringRedisTemplate.opsForHash().putAll("手机4",map);
        redisTemplate.opsForValue().set("dada",100);

    }
}
