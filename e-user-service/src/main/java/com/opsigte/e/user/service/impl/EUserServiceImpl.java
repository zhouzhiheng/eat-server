package com.opsigte.e.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.web.page.PageBean;
import com.opsigte.e.common.core.web.page.PageParam;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import com.opsigte.e.user.api.exception.EUserException;
import com.opsigte.e.user.service.biz.EUserServiceBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


/**
 * <p> @ClassName: <i>EUserServiceImpl</i></p>
 * <p> @Description: <i>用户相关服务实现类</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 18:20</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Service(version = "1.0.0",filter = "traceIdFilter")
public class EUserServiceImpl implements EUserService {

    @Autowired
    private EUserServiceBiz userServiceBiz;

    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;

    @Override
    public EUserEntity getUserById(Integer id) {
       /* try {

            cacheService.get("a");
        } catch (Exception e) {
            if (e instanceof RpcException) {
                throw new EUserException(EUserException.DB_LIST_IS_NULL.getCode(), "rpc异常");
            }
        }*/
        if (id != null) {
            EUserEntity userById = userServiceBiz.getUserById(id);
            log.info("查询用户：{}", JSON.toJSONString(userById));
            log.info("当前redis自增数：{}",cacheService.incr("aaa"));
            return userById;
        }
        return null;
    }

    @Override
    public PageBean listByPage(PageParam pageParam, Map<String, Object> map) {
        return null;
    }

    @Override
    public int update(EUserEntity userEntity) {
        return 0;
    }

    @Override
    public Integer insert(EUserEntity userEntity) {
        return userServiceBiz.insert(userEntity);
    }

    @Override
    public int deleteByPrimaryId(Integer id) {
        return 0;
    }
}
