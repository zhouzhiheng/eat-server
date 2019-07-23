package com.opsigte.eatcacheservice;

import com.opsigte.eatcacheapi.CacheService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EatCacheServiceApplicationTests {

    @Autowired
    private CacheService cacheService;
    @Autowired
    private RedisTemplate redisTemplate;

}
