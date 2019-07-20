package com.opsigte.eat.user.service.dao;

import com.opsigte.eat.user.api.entity.UserEntity;
import com.opsigte.eat.user.service.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> listAll ();
}
