package com.opsigte.e.cache.service;

import com.opsigte.e.cache.api.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ECacheServiceApplicationTests {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedisTemplateSerializer(){
        System.out.println(cacheService.getSet("a", "b"));
    }

    @Test
    public void testHset(){
//        cacheService.hset
        List<String> list = new ArrayList<String>();
        list.add("张三3");
        list.add("李四3");
        cacheService.lSet("list",list);

        System.out.println(cacheService.lGet("list",0,cacheService.lGetListSize("list")));

    }

    @Test
    public void testLock(){
        while (true) {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            System.out.println(cacheService.expireSimpleLock("a", 30) + " time:" + sdf.format(new Date()));

            try {

                Thread.sleep(5000);
            } catch (Exception e) {

            }

        }

    }

}
