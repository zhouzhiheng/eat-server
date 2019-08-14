package com.opsigte.e.user.service.biz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.user.api.entity.EUserEntity;
import com.opsigte.e.user.service.mapper.EUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *<p> @ClassName: <i>EUserServiceBiz</i></p>
 *<p> @Description: <i>用户服务具体业务类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/14 18:37</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Service
public class EUserServiceBiz {

    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;

    @Autowired
    private EUserMapper userMapper;

    public EUserEntity getUserById(Integer id) {
        cacheService.set("a", "c");
        System.out.println("redis缓存：" + cacheService.get("a"));
        return userMapper.selectByPrimaryKey(id);
    }

}
