package com.opsigte.eat.user.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.opsigte.eat.common.core.page.PageBean;
import com.opsigte.eat.common.core.page.PageParam;
import com.opsigte.eat.common.utils.StringUtil;
import com.opsigte.eat.user.api.UserService;
import com.opsigte.eat.user.api.entity.UserEntity;
import com.opsigte.eat.user.service.dao.UserMapper;
import com.opsigte.eatcacheapi.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class UserServiceImpl implements UserService {


    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserEntity> getAllUser() {

        PageHelper.startPage(0, 10);
        List<UserEntity> userAll = userMapper.listAll();

        PageInfo pageInfo = new PageInfo(userAll);
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

        List<UserEntity> userAll = userMapper.listByPage(map);

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


    private static void setPage(PageParam pageParam){
        try {
            PageHelper.startPage(pageParam);
        } catch (Exception e) {
            System.out.println("分页异常");
            e.printStackTrace();

        }
    }

}
