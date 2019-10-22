package com.opsigte.e.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.constant.JwtInfoConstant;
import com.opsigte.e.common.core.utils.SnowFlakeIdWorker;
import com.opsigte.e.common.core.web.response.Resp;
import com.opsigte.e.gateway.annotation.Authorization;
import com.opsigte.e.gateway.jwt.manager.impl.RedisTokenManager;
import com.opsigte.e.gateway.jwt.model.TokenModel;
import com.opsigte.e.order.api.OrderService;
import com.opsigte.e.order.api.entity.OrderEntity;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> @ClassName: <i>EUserController</i></p>
 * <p> @Description: <i>订单服务接口提供类</i></p>
 * <p> @Author: <i>opsig</i></p>
 * <p> @Created date: <i>2019/8/16 23:15</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@RestController
@RequestMapping("api/order")
public class EOrderController {

    @Reference(version = "1.0.0", check = false)
    private EUserService userService;
    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;
    @Reference(version = "1.0.1", check = false)
    private OrderService orderService;

    @ApiOperation(value = "todo",notes = "todo测试接口")
    @RequestMapping(value = "todo",method = {RequestMethod.GET,RequestMethod.POST})
    public String todo (HttpServletRequest request,String name){
        System.out.println("name:" + name);
        return "todo返回211111";
    }

    @PostMapping(value = "insertOrder")
    public Resp insertUser(@RequestBody OrderEntity orderEntity) {
        if (orderEntity == null) {
            return "参数错误";
        }

        int insert = userService.insert(userEntity);
        if (insert != 0) {
            return JSON.toJSONString("添加成功" + insert);
        }
        return "添加失败";

    }



}
