package com.opsigte.e.user.service.biz;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.utils.TraceIdUtil;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import com.opsigte.e.user.service.mapper.EUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        log.info("当前时间:{},biz_tradeId:{}",sdf.format(new Date()),  TraceIdUtil.getTraceId());
        return userMapper.selectByPrimaryKey(id);
    }

    public Integer insert(EUserEntity userEntity) {
        boolean insert1 = cacheService.expireSimpleLock("insert");
        // cacheService.exp
        if (insert1) {
            userEntity.setRemark("");
            userEntity.setCreateTime(new Date());
            userEntity.setUpdatedTime(new Date());
            userEntity.setVersion(1);
            Integer resultId = 0;
            try {
                Integer insert = userMapper.insert(userEntity);
                if (insert == 1) {
                    resultId = userEntity.getId();
                    System.out.println("插入用户成功，当前线程：" + Thread.currentThread().getId() +
                            ",redis中一共统计成功的数量：" + cacheService.incr("insertTrue"));

                } else {
                    System.out.println("插入用户失败，当前线程：" + Thread.currentThread().getId() +
                            ",redis中一共统计失败的数量：" + cacheService.incr("insertFalse"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return resultId;
        } else {
            System.out.println("没有抢到锁：");
            return 0;
        }

    }

}
