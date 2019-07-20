package com.opsigte.eat.user.service.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
    List<T> listByPage(Map<String,Object> map);

    int update(T t);
}
