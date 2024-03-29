package com.opsigte.e.cache.service.core;

import com.opsigte.e.common.core.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * @Project: com.opsigte.e.cache.service.core
 * @Class: RedisManager
 * @Description: 基于spring的redisTemplate工具类
 * @Author: opsigte
 * @Date: 2019/7/24 9:05
 * @version 1.0.0
 */
@Service(value = "redisManager")
public class RedisManager {


    @Autowired
    private RedisTemplate redisTemplate;

	// =============================common============================

    @SuppressWarnings("unchecked")
    public Boolean setnx (String key,String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

	/**
	 * @Title: expire
	 * @Description: TODO 指定缓存失效时间
	 * @param key
	 * @param time
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: getExpire
	 * @Description: TODO 根据key 获取过期时间
	 * @param key 键 不能为null
	 * @return  时间(秒) 返回0代表为永久有效
	 */
    @SuppressWarnings("unchecked")
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}


	/**
	 * @Title: hasKey
	 * @Description: TODO  判断key是否存在
	 * @param key
	 * @return   true 存在 false不存在
	 */
	@SuppressWarnings("unchecked")
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: del
	 * @Description: TODO 删除缓存
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void del(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}

	// ============================String=============================

	/**
	 * @Title: get
	 * @Description: TODO 普通缓存获取
	 * @param key
	 * @return 
	 */
	public Object get(String key) {
		return key == null ? null : redisTemplate.opsForValue().get(key);
	}

	/**
	 * @Title: set
	 * @Description: TODO 普通缓存放入
	 * @param key
	 * @param value
	 * @return 
	 */
	public boolean set(String key, Object value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @Title: set
	 * @Description: TODO 普通缓存放入并设置时间
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
	 * @return true成功 false 失败
	 */
	public boolean set(String key, Object value, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
			} else {
				set(key, value);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * 如果没有根据key获取到值，返回null，并设置key = value。
	 *
	 * @Title getSet
	 * @param [key, value]
	 * @return java.lang.String
	 * @throws
	 */
    public String getSet(String key, String value) {
        if (StringUtil.isEmpty(key)) {
            return null;
        }
        return (String) redisTemplate.opsForValue().getAndSet(key, value);
    }

	/**
	 * @Title: incr
	 * @Description: TODO 递增
	 * @param key 键
	 * @param delta 要增加几(大于0)
	 * @return 
	 */
	public long incr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, delta);
	}


	/**
	 * @Title: decr
	 * @Description: TODO 递减
	 * @param key 键
	 * @param delta 要减少几(小于0)
	 * @return 
	 */
	public long decr(String key, long delta) {
		if (delta < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -delta);
	}




	// ================================Map=================================

	/**
	 * @Title: hget
	 * @Description: TODO HashGet
	 * @param key 键 不能为null
	 * @param item 项 不能为null
	 * @return 
	 */
	public Object hget(String key, String item) {
		return redisTemplate.opsForHash().get(key, item);
	}

	/**
	 * @Title: hmget
	 * @Description: TODO  获取hashKey对应的所有键值
	 * @param key 键
	 * @return  对应的多个键值
	 */
	public Map<Object, Object> hmget(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * @Title: hmset
	 * @Description: TODO HashSet
	 * @param key 键
	 * @param map 对应多个键值
	 * @return  true 成功 false 失败
	 */
	public boolean hmset(String key, Map<String, Object> map) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Title: hmset
	 * @Description: TODO  HashSet 并设置时间
	 * @param key 键
	 * @param map 对应多个键值
	 * @param time 时间(秒)
	 * @return true成功 false失败
	 */
	public boolean hmset(String key, Map<String, Object> map, long time) {
		try {
			redisTemplate.opsForHash().putAll(key, map);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: hset
	 * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
	 * @param key 键
	 * @param item 项
	 * @param value 值
	 * @return  true 成功 false失败
	 */
	public boolean hset(String key, String item, Object value) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: hset
	 * @Description: TODO 向一张hash表中放入数据,如果不存在将创建
	 * @param key 键
	 * @param item 项
	 * @param value 值
	 * @param time 时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
	 * @return true 成功 false失败
	 */
	public boolean hset(String key, String item, Object value, long time) {
		try {
			redisTemplate.opsForHash().put(key, item, value);
			if (time > 0) {
				expire(key, time);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Title: hdel
	 * @Description: TODO 删除hash表中的值
	 * @param key 键 不能为null
	 * @param item 项 可以使多个 不能为null
	 */
	public void hdel(String key, Object... item) {
		redisTemplate.opsForHash().delete(key, item);
	}

	/**
	 * @Title: hHasKey
	 * @Description: TODO 判断hash表中是否有该项的值
	 * @param key 键 不能为null
	 * @param item 项 不能为null
	 * @return true 存在 false不存在
	 */
	public boolean hHasKey(String key, String item) {
		return redisTemplate.opsForHash().hasKey(key, item);
	}


	/**
	 * @Title: hincr
	 * @Description: TODO hash递增 如果不存在,就会创建一个 并把新增后的值返回
	 * @param key 键
	 * @param item 项
	 * @param by 要增加几(大于0)
	 * @return 
	 */
	public double hincr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, by);
	}

	/**
	 * @Title: hdecr
	 * @Description: TODO hash递减
	 * @param key 键
	 * @param item  项
	 * @param by  要减少记(小于0)
	 * @return 
	 */
	public double hdecr(String key, String item, double by) {
		return redisTemplate.opsForHash().increment(key, item, -by);
	}

	// ============================set=============================

	/**
	 * @Title: sGet
	 * @Description: TODO 根据key获取Set中的所有值
	 * @param key 键
	 * @return 
	 */
	public Set<Object> sGet(String key) {
		try {
			return redisTemplate.opsForSet().members(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * @Title: sHasKey
	 * @Description: TODO 根据value从一个set中查询,是否存在
	 * @param key 键
	 * @param value 值
	 * @return  true 存在 false不存在
	 */
	public boolean sHasKey(String key, Object value) {
		try {
			return redisTemplate.opsForSet().isMember(key, value);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: sSet
	 * @Description: TODO 将数据放入set缓存
	 * @param key 键
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long sSet(String key, Object... values) {
		try {
			return redisTemplate.opsForSet().add(key, values);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Title: sSetAndTime
	 * @Description: TODO 将set数据放入缓存
	 * @param key 键
	 * @param time 时间(秒)
	 * @param values 值 可以是多个
	 * @return 成功个数
	 */
	public long sSetAndTime(String key, long time, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().add(key, values);
			if (time > 0)
				expire(key, time);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	/**
	 * @Title: sGetSetSize
	 * @Description: TODO 获取set缓存的长度
	 * @param key 键
	 * @return 
	 */
	public long sGetSetSize(String key) {
		try {
			return redisTemplate.opsForSet().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


	/**
	 * @Title: setRemove
	 * @Description: TODO 移除值为value的
	 * @param key  键
	 * @param values 值 可以是多个
	 * @return  移除的个数
	 */
	public long setRemove(String key, Object... values) {
		try {
			Long count = redisTemplate.opsForSet().remove(key, values);
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	// ===============================list=================================


	/**
	 * @Title: lGet
	 * @Description: TODO 获取list缓存的内容
	 * @param key 键
	 * @param start 开始
	 * @param end 结束 0 到 -1代表所有值
	 * @return 
	 */
	public List<Object> lGet(String key, long start, long end) {
		try {
			return redisTemplate.opsForList().range(key, start, end);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * @Title: lGetListSize
	 * @Description: TODO 获取list缓存的长度
	 * @param key  键
	 * @return 
	 */
	public long lGetListSize(String key) {
		try {
			return redisTemplate.opsForList().size(key);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Title: lGetIndex
	 * @Description: TODO 通过索引 获取list中的值
	 * @param key 键
	 * @param index 索引 index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
	 * @return 
	 */
	public Object lGetIndex(String key, long index) {
		try {
			return redisTemplate.opsForList().index(key, index);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}



	/**
	 * @Title: lSet
	 * @Description: TODO 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @return 时间(秒)
	 */
	public boolean lSet(String key, Object value) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @Title: lSet
	 * @Description: TODO 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time  时间(秒)
	 * @return 
	 */
	public boolean lSet(String key, Object value, long time) {
		try {
			redisTemplate.opsForList().rightPush(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: lSet
	 * @Description: TODO 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @return  时间(秒)
	 */
	public boolean lSet(String key, List<Object> value) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: lSet
	 * @Description: TODO 将list放入缓存
	 * @param key 键
	 * @param value 值
	 * @param time 时间(秒)
	 * @return 
	 */
	public boolean lSet(String key, List<Object> value, long time) {
		try {
			redisTemplate.opsForList().rightPushAll(key, value);
			if (time > 0)
				expire(key, time);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: lUpdateIndex
	 * @Description: TODO 根据索引修改list中的某条数据
	 * @param key    键
	 * @param index  索引
	 * @param value  值
	 * @return 
	 */
	public boolean lUpdateIndex(String key, long index, Object value) {
		try {
			redisTemplate.opsForList().set(key, index, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	/**
	 * @Title: lRemove
	 * @Description: TODO 移除N个值为value
	 * @param key   键
	 * @param count 移除多少个
	 * @param value 值
	 * @return      移除的个数
	 */
	public long lRemove(String key, long count, Object value) {
		try {
			Long remove = redisTemplate.opsForList().remove(key, count, value);
			return remove;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Title: rpush
	 * @Description: TODO 往队列尾部中push一个值
	 * @param key
	 * @param value
	 * @return 
	 */
	public long rpush(String key, Object value) {
		try {
			Long r = redisTemplate.opsForList().rightPush(key, value);
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * @Title: lpop
	 * @Description: TODO 从队列头部获取一个值
	 * @param key
	 * @return 
	 */
	public Object lpop(String key) {
		try {
			return redisTemplate.opsForList().leftPop(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
