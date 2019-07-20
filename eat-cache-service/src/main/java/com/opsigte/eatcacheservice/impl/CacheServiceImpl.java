package com.opsigte.eatcacheservice.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.eat.common.utils.StringUtil;
import com.opsigte.eatcacheapi.CacheService;
import com.opsigte.eatcacheservice.core.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Project: com.opsigte.eatcacheservice.impl
 * @Class: CacheServiceImpl
 * @Description: 缓存服务Dubbo接口实现类
 * @Author: opsigte
 * @Date: 2019/7/9 18:46
 * @version
 */
@Service(version = "1.0.0",timeout = 30000)
public class CacheServiceImpl implements CacheService {

	@Autowired
	private RedisManager redisManager;


    @Override
    public Boolean setnx(String key, String value) {
        if (StringUtil.isEmpty(key)) {
            return false;
        }
        return redisManager.setnx(key, value);
    }

    @Override
	public boolean expire(String key, long time) {
		if (StringUtil.isEmpty(key)) {
			return false;
		}
		return redisManager.expire(key, time);
	}

	@Override
	public long getExpire(String key) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.getExpire(key);
	}

	@Override
	public boolean hasKey(String key) {
		if (StringUtil.isEmpty(key)) {
			return false;
		}
		return redisManager.hasKey(key);
	}

	@Override
	public void del(String... key) {
		redisManager.del(key);
	}

	@Override
	public Object get(String key) {
		if (StringUtil.isEmpty(key)) {
			return null;
		}
		return redisManager.get(key);
	}

    @Override
    public String get(String key, String flag) {
        if (StringUtil.isEmpty(key) || StringUtil.isEmpty(flag)) {
            return null;
        }
        Object obj = redisManager.get(key);
        if (obj == null) {
            return null;
        }
        return (String) obj;
    }

    @Override
	public boolean set(String key, Object value) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value)) {
			return false;
		}
		return redisManager.set(key, value);
	}

	@Override
	public boolean set(String key, Object value, long time) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(value) || time == 0) {
			return false;
		}
		return redisManager.set(key, value, time);
	}

    @Override
    public String getSet(String key, String value) {
        return redisManager.getSet(key, value);
    }

    @Override
	public long incr(String key, long delta) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.incr(key, delta);
	}

	@Override
	public long decr(String key, long delta) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.decr(key, delta);
	}

	@Override
	public Object hget(String key, String item) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item)) {
			return null;
		}
		return redisManager.hget(key, item);
	}

	@Override
	public Map<Object, Object> hmget(String key) {
		if (StringUtil.isEmpty(key)) {
			return null;
		}
		return redisManager.hmget(key);
	}

	@Override
	public boolean hmset(String key, Map<String, Object> map) {
		if (StringUtil.isEmpty(key) || map == null || map.size() < 1) {
			return false;
		}
		return redisManager.hmset(key, map);
	}

	@Override
	public boolean hmset(String key, Map<String, Object> map, long time) {
		if (StringUtil.isEmpty(key) || map == null || map.size() < 1 || time == 0) {
			return false;
		}
		return redisManager.hmset(key, map, time);
	}

	@Override
	public boolean hset(String key, String item, Object value) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item) || StringUtil.isEmpty(value)) {
			return false;
		}
		return redisManager.hset(key, item, value);
	}

	@Override
	public boolean hset(String key, String item, Object value, long time) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item) || StringUtil.isEmpty(value) || time == 0) {
			return false;
		}
		return redisManager.hset(key, item, value, time);
	}

	@Override
	public void hdel(String key, Object... item) {
		if (StringUtil.isEmpty(key)) {
			return ;
		}
		redisManager.hdel(key, item);
	}

	@Override
	public boolean hHasKey(String key, String item) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item)) {
			return false;
		}
		return redisManager.hHasKey(key, item);
	}

	@Override
	public double hincr(String key, String item, double by) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item)) {
			return 0;
		}
		return redisManager.hincr(key, item, by);
	}

	@Override
	public double hdecr(String key, String item, double by) {
		if (StringUtil.isEmpty(key) || StringUtil.isEmpty(item)) {
			return 0;
		}
		return redisManager.hdecr(key, item, by);
	}

	@Override
	public Set<Object> sGet(String key) {
		if (StringUtil.isEmpty(key)) {
			return null;
		}
		return redisManager.sGet(key);
	}

	@Override
	public boolean sHasKey(String key, Object value) {
		if (StringUtil.isEmpty(key) || value == null) {
			return false;
		}
		return redisManager.sHasKey(key, value);
	}

	@Override
	public long sSet(String key, Object... values) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.sSet(key, values);
	}

	@Override
	public long sSetAndTime(String key, long time, Object... values) {
		if (StringUtil.isEmpty(key) || time == 0) {
			return 0;
		}
		return redisManager.sSetAndTime(key, time, values);
	}

	@Override
	public long sGetSetSize(String key) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.sGetSetSize(key);
	}

	@Override
	public long setRemove(String key, Object... values) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.setRemove(key, values);
	}

	@Override
	public List<Object> lGet(String key, long start, long end) {
		if (StringUtil.isEmpty(key)) {
			return null;
		}
		return redisManager.lGet(key, start, end);
	}

	@Override
	public long lGetListSize(String key) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.lGetListSize(key);
	}

	@Override
	public Object lGetIndex(String key, long index) {
		if (StringUtil.isEmpty(key)) {
			return 0;
		}
		return redisManager.lGetIndex(key, index);
	}

	@Override
	public boolean lSet(String key, Object value) {
		if (StringUtil.isEmpty(key)) {
			return false;
		}
		return redisManager.lSet(key, value);
	}

	@Override
	public boolean lSet(String key, Object value, long time) {
		if (StringUtil.isEmpty(key) || value == null || time == 0) {
			return false;
		}
		return redisManager.lSet(key, value, time);
	}

	@Override
	public boolean lSet(String key, List<Object> value) {
		if (StringUtil.isEmpty(key) || value == null || value.size() < 1) {
			return false;
		}
		return redisManager.lSet(key, value);
	}

	@Override
	public boolean lSet(String key, List<Object> value, long time) {
		if (StringUtil.isEmpty(key) || value == null || value.size() < 1 || time == 0) {
			return false;
		}
		return redisManager.lSet(key, value, time);
	}

	@Override
	public boolean lUpdateIndex(String key, long index, Object value) {
		if (StringUtil.isEmpty(key) || value == null ) {
			return false;
		}
		return redisManager.lUpdateIndex(key, index, value);
	}

	@Override
	public long lRemove(String key, long count, Object value) {
		if (StringUtil.isEmpty(key) || value == null ) {
			return 0;
		}
		return redisManager.lRemove(key, count, value);
	}

	@Override
	public long rpush(String key, Object value) {
		if (StringUtil.isEmpty(key) || value == null ) {
			return 0;
		}
		return redisManager.rpush(key, value);
	}

	@Override
	public Object lpop(String key) {
		if (StringUtil.isEmpty(key)) {
			return null;
		}
		return redisManager.lpop(key);
	}

}
