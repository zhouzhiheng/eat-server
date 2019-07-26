package com.opsigte.e.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.page.PageBean;
import com.opsigte.e.common.core.page.PageParam;
import com.opsigte.e.common.core.utils.StringUtil;
import com.opsigte.e.user.api.UserService;
import com.opsigte.e.user.api.entity.UserEntity;
import com.opsigte.e.user.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Project: com.opsigte.e.user.service.impl
 * @Class: UserServiceImpl
 * @Description: 用户相关服务实现类
 * @Author: opsigte
 * @Date: 2019/7/24 21:36
 * @version 1.0.0
 */
@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {


    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUser() {

        List<UserEntity> userAll = userMapper.selectListAll();

        System.out.println(JSON.toJSONString(userAll));
        return userAll;
    }



    @Override
    public UserEntity getUserByUid(String uid) {
        try {
            Object obj = cacheService.get("getBy");
            if (StringUtil.isEmpty(obj)) {
                List<UserEntity> allUser = getAllUser();
                for (UserEntity entity : allUser) {
                    if (uid.equals(entity.getId())) {
                        return entity;
                    }
                }
            } else {
                System.out.println("从缓存中拿到数据");
                return JSON.parseObject(JSON.toJSONString(obj), UserEntity.class);
            }
        } catch (RpcException re) {
            List<UserEntity> allUser = getAllUser();
            for (UserEntity entity : allUser) {
                if (uid.equals(entity.getId())) {
                    return entity;
                }
            }
            System.out.println("rpc exception");

        } catch (Exception e) {
            System.out.println("exception");
        }


        return null;
    }

    @Override
    public PageBean listByPage(PageParam pageParam,Map<String,Object> map) {
        setPage(pageParam);
//        PageHelper.startPage(5, 5);

        List<UserEntity> userAll = userMapper.selectListByPage(map);

        PageBean<UserEntity> pageBean = PageBean.restPage(userAll);
        System.out.println(JSON.toJSONString(pageBean));
        return pageBean;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public int update(UserEntity userEntity) {

        int update = userMapper.update(userEntity);
        return update;
    }

    @Override
    public Integer insert(UserEntity userEntity) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date requestDate = new Date();
        System.out.println("请求时间:" + sdf.format(requestDate));

        // 模拟业务处理
        boolean a = cacheService.expireSimpleLock("a", 60);
        if (a) {

            try {
                Thread.sleep(5000);
                Integer insert = userMapper.insert(userEntity);
                if (insert == 1) {
                    // 释放锁
                    cacheService.unLockExpireSimpleLock("a");
                    System.out.println("获取锁成功，插入成功：current user id :" + userEntity.getId() + ",," + sdf.format(new Date()));
                    return userEntity.getId();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("此次插入没有抢到锁，插入失败:" + sdf.format(requestDate));;
        }

        return 0;

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteByPrimaryId(Integer id) {
        int i = userMapper.deleteByPrimaryId(id);

        int a = 1 / 0;

        return i;
    }


    private static void setPage(PageParam pageParam){
        try {
            PageHelper.startPage(pageParam);
        } catch (Exception e) {
            System.out.println("分页异常");
            e.printStackTrace();

        }
    }

}
