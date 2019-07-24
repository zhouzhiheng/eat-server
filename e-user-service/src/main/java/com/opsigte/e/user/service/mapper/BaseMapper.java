package com.opsigte.e.user.service.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    List<T> listByPage(Map<String,Object> map);

    int update(T t);

    int insert(T t);
}
