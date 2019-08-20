package com.opsigte.e.user.api;

import com.opsigte.e.common.core.web.page.PageBean;
import com.opsigte.e.common.core.web.page.PageParam;
import com.opsigte.e.user.api.entity.EUserEntity;

import java.util.Map;

/**
 * <p> @ClassName: <i>UserService</i></p>
 * <p> @Description: <i>用户服务接口</i></p>
 * <p> @Author: <i>opsigte</i></p>
 * <p> @Created date: <i>2019/8/14 16:57</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
public interface EUserService {

    EUserEntity getUserById(Integer id);

    PageBean listByPage(PageParam pageParam, Map<String,Object> map);

    int update(EUserEntity userEntity);

    Integer insert(EUserEntity userEntity);

    int deleteByPrimaryId(Integer id);

}
