package com.opsigte.eat.user.api;

import com.opsigte.eat.common.core.page.PageBean;
import com.opsigte.eat.common.core.page.PageParam;
import com.opsigte.eat.user.api.entity.UserEntity;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<UserEntity> getAllUser();

    UserEntity getUserByUid(String uid);

    PageBean listByPage(PageParam pageParam, Map<String,Object> map);

    int update(UserEntity userEntity);

    Integer insert(UserEntity userEntity);

}
