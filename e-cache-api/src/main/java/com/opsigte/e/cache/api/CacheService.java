package com.opsigte.e.cache.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CacheService {


    /**
     * 设置一个存在有效期的锁
     * @param key    redis key
     * @param expire 过期时间，单位秒
     * @return true 加锁成功（获取到锁）;false 加锁失败（没有获取到锁）
     */
    boolean expireSimpleLock(String key, int expire);

    /**
     * 续时锁：
     * 1.如果抢到锁，返回true
     * 2.如果没抢到锁，根据当前时间判断锁的旧的时间是否已经过期，如果过期，设置新的时间，返回true；入宫没过期，返回false
     * @param key redis key
     * @return true 加锁成功（获取到锁）;false 加锁失败（没有获取到锁）
     */
    boolean continueExpireLock(String key);


    /**
     * 续时锁：
     * 1.如果抢到锁，返回true
     * 2.如果没抢到锁，根据当前时间判断锁的旧的时间是否已经过期，如果过期，设置新的时间，返回true；入宫没过期，返回false
     * @param key redis key
     * @param expire 过期时间，单位秒
     * @return true 加锁成功（获取到锁）;false 加锁失败（没有获取到锁）
     */
    boolean continueExpireLock(String key, int expire);

    /**
     * 释放 有效期锁
     * @param key 锁的key
     */
    void unLockExpireSimpleLock(String key);


    /**
     * 释放 根据有效时间判断是否加锁 的锁
     *
     * @param key 锁的key
     */
    void unLockContinueExpireLock(String key);

    /**
     * 该方法会在没有key时，设置key;存在key时返回false
     *
     * @param [key, expire]
     * @return java.lang.Boolean
     * @throws
     * @Title setnx
     */
    boolean setnx(String key, String value);

    /**
     * @param key
     * @param time
     * @Title: expire
     * @Description: TODO 指定缓存失效时间
     * @return 
     */
    boolean expire(String key, long time);


    /**
     * @param key 键 不能为null
     * @Title: getExpire
     * @Description: TODO 根据key 获取过期时间
     * @return  时间(秒) 返回0代表为永久有效
     */
    long getExpire(String key);


    /**
     * @param key
     * @Title: hasKey
     * @Description: TODO  判断key是否存在
     * @return  true 存在 false不存在
     */
    boolean hasKey(String key);


    /**
     * @param key 可以传一个值 或多个
     * @Title: del
     * @Description: TODO 删除缓存
     */
    void del(String... key);

    // ============================String=============================

    /**
     * @param key
     * @Title: get
     * @Description: TODO 普通缓存获取
     * @return 
     */
    Object get(String key);

    /**
     * 从缓存中拿值强转String返回
     *
     * @param [key, flag]
     * @return java.lang.String
     * @throws
     * @Title get
     */
    String get(String key, String flag);


    /**
     * @param key
     * @param value
     * @Title: set
     * @Description: TODO 普通缓存放入
     * @return 
     */
    boolean set(String key, Object value);

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     * @Title: set
     * @Description: TODO 普通缓存放入并设置时间
     */
    boolean set(String key, Object value, long time);

    /**
     * 如果没有根据key获取到值，返回null，并设置key = value。
     *
     * @param [key, value]
     * @return java.lang.String
     * @throws
     * @Title getSet
     */
    String getSet(String key, String value);


    /**
     * @param key   键
     * @param delta 要增加几(大于0)
     * @Title: incr
     * @Description: TODO 递增
     * @return 
     */
    long incr(String key, long delta);

    /**
     * @param key   键
     * @param delta 要减少几(小于0)
     * @Title: decr
     * @Description: TODO 递减
     * @return 
     */
    long decr(String key, long delta);


    // ================================Map=================================

    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @Title: hget
     * @Description: TODO HashGet
     * @return 
     */
    Object hget(String key, String item);

    /**
     * @param key 键
     * @Title: hmget
     * @Description: TODO  获取hashKey对应的所有键值
     * @return  对应的多个键值
     */
    Map<Object, Object> hmget(String key);

    /**
     * @param key 键
     * @param map 对应多个键值
     * @Title: hmset
     * @Description: TODO HashSet
     * @return  true 成功 false 失败
     */
    boolean hmset(String key, Map<String, Object> map);

    /**
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @Title: hmset
     * @Description: TODO  HashSet 并设置时间
     * @return true成功 false失败
     */
    boolean hmset(String key, Map<String, Object> map, long time);


    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @Title: hset
     * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
     * @return  true 成功 false失败
     */
    boolean hset(String key, String item, Object value);


    /**
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     * @Title: hset
     * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
     */
    boolean hset(String key, String item, Object value, long time);

    /**
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     * @Title: hdel
     * @Description: TODO 删除hash表中的值
     */
    void hdel(String key, Object... item);

    /**
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     * @Title: hHasKey
     * @Description: TODO 判断hash表中是否有该项的值
     */
    boolean hHasKey(String key, String item);


    /**
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @Title: hincr
     * @Description: TODO hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @return 
     */
    double hincr(String key, String item, double by);

    /**
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @Title: hdecr
     * @Description: TODO hash递减
     * @return 
     */
    double hdecr(String key, String item, double by);

    // ============================set=============================

    /**
     * @param key 键
     * @Title: sGet
     * @Description: TODO 根据key获取Set中的所有值
     * @return 
     */
    Set<Object> sGet(String key);


    /**
     * @param key   键
     * @param value 值
     * @Title: sHasKey
     * @Description: TODO 根据value从一个set中查询,是否存在
     * @return  true 存在 false不存在
     */
    boolean sHasKey(String key, Object value);


    /**
     * @param key    键
     * @param values 值 可以是多个
     * @Title: sSet
     * @Description: TODO 将数据放入set缓存
     * @return 成功个数
     */
    long sSet(String key, Object... values);

    /**
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @Title: sSetAndTime
     * @Description: TODO 将set数据放入缓存
     * @return 成功个数
     */
    long sSetAndTime(String key, long time, Object... values);


    /**
     * @param key 键
     * @Title: sGetSetSize
     * @Description: TODO 获取set缓存的长度
     * @return 
     */
    long sGetSetSize(String key);


    /**
     * @param key    键
     * @param values 值 可以是多个
     * @Title: setRemove
     * @Description: TODO 移除值为value的
     * @return  移除的个数
     */
    long setRemove(String key, Object... values);
    // ===============================list=================================


    /**
     * @param key   键
     * @param start 开始
     * @param end   结束 0 到 -1代表所有值
     * @Title: lGet
     * @Description: TODO 获取list缓存的内容
     * @return 
     */
    List<Object> lGet(String key, long start, long end);


    /**
     * @param key 键
     * @Title: lGetListSize
     * @Description: TODO 获取list缓存的长度
     * @return 
     */
    long lGetListSize(String key);

    /**
     * @param key   键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @Title: lGetIndex
     * @Description: TODO 通过索引 获取list中的值
     * @return 
     */
    Object lGetIndex(String key, long index);


    /**
     * @param key   键
     * @param value 值
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @return 时间(秒)
     */
    boolean lSet(String key, Object value);

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @return 
     */
    boolean lSet(String key, Object value, long time);


    /**
     * @param key   键
     * @param value 值
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @return  时间(秒)
     */
    boolean lSet(String key, List<Object> value);

    /**
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @return 
     */
    boolean lSet(String key, List<Object> value, long time);


    /**
     * @param key   键
     * @param index 索引
     * @param value 值
     * @Title: lUpdateIndex
     * @Description: TODO 根据索引修改list中的某条数据
     * @return 
     */
    boolean lUpdateIndex(String key, long index, Object value);


    /**
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @Title: lRemove
     * @Description: TODO 移除N个值为value
     * @return  移除的个数
     */
    long lRemove(String key, long count, Object value);

    /**
     * @param key   件
     * @param value 值
     * @Title: rpush
     * @Description: TODO 往队列尾部中push一个值
     * @return  
     */
    long rpush(String key, Object value);

    /**
     * @param key
     * @Title: lpop
     * @Description: TODO 返回第一个（头部）元素
     * @return  
     */
    Object lpop(String key);

}
