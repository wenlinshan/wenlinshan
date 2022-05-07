package com.wen;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wen.domain.Goods;
import com.wen.service.GoodsService;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
       goodsService.save(Goods.builder().name("mac").quantity(100).build());
    }

    @Test
    public void testSet(){
        List<Goods> list = new ArrayList<>();
        List<Goods> goods = goodsService.list();
        Map<String,Object> map = new HashMap<>();
        map.put("date",new Date());
        map.put("list",goods);
        redisTemplate.opsForHash().putAll("goodT",map);
    }

    @Test
    public void testGet(){

        Map<Object, Object> t = redisTemplate.opsForHash().entries("goodT");
        System.out.println(t);
        Long o = (Long) redisTemplate.opsForHash().get("goodT", "date");
        redisTemplate.delete("ss");
        System.out.println();
    }

    @Test
    public void testGet2(){
        List<Goods> goods = goodsService.list();
        Map<String,Object> map = new HashMap<>();
        Date date = new Date();
        map.put("date",date);
        map.put("list",goods);
        redisTemplate.opsForHash().putAll("goodTY",map);
        redisTemplate.expire("goodTY",10, TimeUnit.SECONDS);
        System.out.println();
    }

}
