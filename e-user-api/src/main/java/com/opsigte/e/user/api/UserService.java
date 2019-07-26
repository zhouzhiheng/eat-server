package com.opsigte.e.user.api;

import com.opsigte.e.common.core.page.PageBean;
import com.opsigte.e.common.core.page.PageParam;
import com.opsigte.e.user.api.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * @Project: com.opsigte.e.user.api
 * @Class: UserService
 * @Description: 用户相关接口
 * @Author: opsigte
 * @Date: 2019/7/24 21:35
 * @version 1.0.0
 */
public interface UserService {

    List<UserEntity> getAllUser();

    UserEntity getUserByUid(String uid);

    PageBean listByPage(PageParam pageParam, Map<String,Object> map);

    int update(UserEntity userEntity);

    Integer insert(UserEntity userEntity);

    int deleteByPrimaryId(Integer id);

}
