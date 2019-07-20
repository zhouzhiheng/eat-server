package com.opsigte.eatcacheapi;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CacheService {


    /**
     * 该方法会在没有key时，设置key;存在key时返回false
     *
     * @Title setnx
     * @param [key, expire]
     * @return java.lang.Boolean
     * @throws
     */
    public Boolean setnx (String key,String expire);

    /**
     * @Title: expire
     * @Description: TODO 指定缓存失效时间
     * @param key
     * @param time
     * @return 
     */
    public boolean expire(String key, long time);


    /**
     * @Title: getExpire
     * @Description: TODO 根据key 获取过期时间
     * @param key 键 不能为null
     * @return  时间(秒) 返回0代表为永久有效
     */
    public long getExpire(String key);


    /**
     * @Title: hasKey
     * @Description: TODO  判断key是否存在
     * @param key
     * @return   true 存在 false不存在
     */
    public boolean hasKey(String key);


    /**
     * @Title: del
     * @Description: TODO 删除缓存
     * @param key 可以传一个值 或多个
     */
    public void del(String... key) ;

    // ============================String=============================

    /**
     * @Title: get
     * @Description: TODO 普通缓存获取
     * @param key
     * @return 
     */
    public Object get(String key);

    /**
     * 从缓存中拿值强转String返回
     *
     * @Title get
     * @param [key, flag]
     * @return java.lang.String
     * @throws
     */
    public String get(String key, String flag);



    /**
     * @Title: set
     * @Description: TODO 普通缓存放入
     * @param key
     * @param value
     * @return 
     */
    public boolean set(String key, Object value);

    /**
     * @Title: set
     * @Description: TODO 普通缓存放入并设置时间
     * @param key 键
     * @param value 值
     * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time) ;

    /**
     * 如果没有根据key获取到值，返回null，并设置key = value。
     *
     * @Title getSet
     * @param [key, value]
     * @return java.lang.String
     * @throws
     */
    public String getSet(String key, String value);



    /**
     * @Title: incr
     * @Description: TODO 递增
     * @param key 键
     * @param delta 要增加几(大于0)
     * @return 
     */
    public long incr(String key, long delta);

    /**
     * @Title: decr
     * @Description: TODO 递减
     * @param key 键
     * @param delta 要减少几(小于0)
     * @return 
     */
    public long decr(String key, long delta);




    // ================================Map=================================

    /**
     * @Title: hget
     * @Description: TODO HashGet
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return 
     */
    public Object hget(String key, String item);

    /**
     * @Title: hmget
     * @Description: TODO  获取hashKey对应的所有键值
     * @param key 键
     * @return  对应的多个键值
     */
    public Map<Object, Object> hmget(String key);

    /**
     * @Title: hmset
     * @Description: TODO HashSet
     * @param key 键
     * @param map 对应多个键值
     * @return  true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) ;

    /**
     * @Title: hmset
     * @Description: TODO  HashSet 并设置时间
     * @param key 键
     * @param map 对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time);


    /**
     * @Title: hset
     * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @return  true 成功 false失败
     */
    public boolean hset(String key, String item, Object value);


    /**
     * @Title: hset
     * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
     * @param key 键
     * @param item 项
     * @param value 值
     * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time);

    /**
     * @Title: hdel
     * @Description: TODO 删除hash表中的值
     * @param key 键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) ;

    /**
     * @Title: hHasKey
     * @Description: TODO 判断hash表中是否有该项的值
     * @param key 键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) ;


    /**
     * @Title: hincr
     * @Description: TODO hash递增 如果不存在,就会创建一个 并把新增后的值返回
     * @param key 键
     * @param item 项
     * @param by 要增加几(大于0)
     * @return 
     */
    public double hincr(String key, String item, double by);

    /**
     * @Title: hdecr
     * @Description: TODO hash递减
     * @param key 键
     * @param item  项
     * @param by  要减少记(小于0)
     * @return 
     */
    public double hdecr(String key, String item, double by) ;

    // ============================set=============================

    /**
     * @Title: sGet
     * @Description: TODO 根据key获取Set中的所有值
     * @param key 键
     * @return 
     */
    public Set<Object> sGet(String key);


    /**
     * @Title: sHasKey
     * @Description: TODO 根据value从一个set中查询,是否存在
     * @param key 键
     * @param value 值
     * @return  true 存在 false不存在
     */
    public boolean sHasKey(String key, Object value);


    /**
     * @Title: sSet
     * @Description: TODO 将数据放入set缓存
     * @param key 键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSet(String key, Object... values);

    /**
     * @Title: sSetAndTime
     * @Description: TODO 将set数据放入缓存
     * @param key 键
     * @param time 时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public long sSetAndTime(String key, long time, Object... values);


    /**
     * @Title: sGetSetSize
     * @Description: TODO 获取set缓存的长度
     * @param key 键
     * @return 
     */
    public long sGetSetSize(String key);


    /**
     * @Title: setRemove
     * @Description: TODO 移除值为value的
     * @param key  键
     * @param values 值 可以是多个
     * @return  移除的个数
     */
    public long setRemove(String key, Object... values);
    // ===============================list=================================


    /**
     * @Title: lGet
     * @Description: TODO 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return 
     */
    public List<Object> lGet(String key, long start, long end);


    /**
     * @Title: lGetListSize
     * @Description: TODO 获取list缓存的长度
     * @param key  键
     * @return 
     */
    public long lGetListSize(String key);

    /**
     * @Title: lGetIndex
     * @Description: TODO 通过索引 获取list中的值
     * @param key 键
     * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return 
     */
    public Object lGetIndex(String key, long index);



    /**
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @param key 键
     * @param value 值
     * @return 时间(秒)
     */
    public boolean lSet(String key, Object value);

    /**
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time  时间(秒)
     * @return 
     */
    public boolean lSet(String key, Object value, long time);


    /**
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @param key 键
     * @param value 值
     * @return  时间(秒)
     */
    public boolean lSet(String key, List<Object> value) ;

    /**
     * @Title: lSet
     * @Description: TODO 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return 
     */
    public boolean lSet(String key, List<Object> value, long time);


    /**
     * @Title: lUpdateIndex
     * @Description: TODO 根据索引修改list中的某条数据
     * @param key    键
     * @param index  索引
     * @param value  值
     * @return 
     */
    public boolean lUpdateIndex(String key, long index, Object value) ;


    /**
     * @Title: lRemove
     * @Description: TODO 移除N个值为value
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return      移除的个数
     */
    public long lRemove(String key, long count, Object value);

    /**
     * @Title: rpush
     * @Description: TODO 往队列尾部中push一个值
     * @param key 件
     * @param value 值
     * @return  
     */
    public long rpush(String key, Object value);

    /**
     * @Title: lpop
     * @Description: TODO 返回第一个（头部）元素
     * @param key
     * @return  
     */
    public Object lpop(String key);

}
