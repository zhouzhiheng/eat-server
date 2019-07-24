package com.opsigte.e.user.service.dao;

import com.opsigte.e.user.api.entity.UserEntity;
import com.opsigte.e.user.service.mapper.BaseMapper;

import java.util.List;

public interface UserMapper extends BaseMapper<UserEntity> {
    List<UserEntity> listAll ();
}
