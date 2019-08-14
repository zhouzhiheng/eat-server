package com.opsigte.e.user.service.biz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import com.opsigte.e.user.service.mapper.EUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *<p> @ClassName: <i>EUserServiceBiz</i></p>
 *<p> @Description: <i>用户服务具体业务类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/14 18:37</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Service
public class EUserServiceBiz {

    private static final Logger log = LoggerFactory.getLogger(EUserServiceBiz.class);


    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;

    @Autowired
    private EUserMapper userMapper;

    public EUserEntity getUserById(Integer id) {
        try {
            cacheService.set("a", "c");
            System.out.println("redis缓存：" + cacheService.get("a"));
        } catch (Exception e) {
            log.error("dubbo服务异常", e);
        }
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer insert(EUserEntity userEntity) {
        userEntity.setRemark("");
        userEntity.setCreateTime(new Date());
        userEntity.setUpdatedTime(new Date());
        userEntity.setVersion(1);
        return userMapper.insert(userEntity);
    }

}
