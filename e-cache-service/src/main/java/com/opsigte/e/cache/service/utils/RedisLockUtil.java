package com.opsigte.e.cache.service.utils;

import com.opsigte.e.cache.api.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


/**
 * @Project: com.opsigte.e.cache.service.utils
 * @Class: RedisLockUtil
 * @Description: redis分布式锁
 * @Author: opsigte
 * @Date: 2019/7/24 9:07
 * @version 1.0.0
 */
@Component
public final class RedisLockUtil {

    @Autowired
    private CacheService cacheService;

    private static CacheService sCacheService;

    @PostConstruct
    public void beforeInit() {
        sCacheService = cacheService;
    }

    private static final int defaultExpire = 60;

    private RedisLockUtil() {
    }

    /**
     * 设置一个存在有效期的锁
     *
     * @param key    redis key
     * @param expire 过期时间，单位秒
     * @return true:加锁成功，false，加锁失败
     */
    public static boolean expireSimpleLock(String key, int expire) {

        boolean flag = sCacheService.setnx(key, "1");

        if (flag) {
            sCacheService.expire(key, expire);
            return true;
        }

        return false;
    }

    public static boolean judgeIsExpireLock(String key) {
        return judgeIsExpireLock(key, defaultExpire);
    }

    /**
     * 根据有效期时间判断是否加锁
     *
     * @param key    redis key
     * @param expire 过期时间，单位秒
     * @return true:加锁成功，false，加锁失败
     */
    public static boolean judgeIsExpireLock(String key, int expire) {

        long value = System.currentTimeMillis() + expire;
        boolean flag = sCacheService.setnx(key, String.valueOf(value));

        if (flag) {
            return true;
        }

        long oldExpireTime = Long.parseLong(sCacheService.get(key, "0"));
        if (oldExpireTime < System.currentTimeMillis()) {
             //超时
            long newExpireTime = System.currentTimeMillis() + expire;
            long currentExpireTime = Long.parseLong(sCacheService.getSet(key, String.valueOf(newExpireTime)));
            if (currentExpireTime == oldExpireTime) {
                return true;
            }
        }
        return false;
    }

    /**
     * 释放 有效期锁
     * @param key
     */
    public static void unLockExpireSimpleLock(String key) {
        sCacheService.del(key);
    }


    /**
     *  释放 根据有效期时间判断是否加锁
     * @param key
     */
    public static void unLockJudgeIsExpireLock(String key) {
        long oldExpireTime = Long.parseLong(sCacheService.get(key, "0"));
        if (oldExpireTime > System.currentTimeMillis()) {
            sCacheService.del(key);
        }
    }
}