package com.opsigte.e.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.cache.api.CacheService;
import com.opsigte.e.common.core.constant.JwtInfoConstant;
import com.opsigte.e.common.core.utils.SnowFlakeIdWorker;
import com.opsigte.e.common.core.web.response.Resp;
import com.opsigte.e.gateway.annotation.Authorization;
import com.opsigte.e.gateway.jwt.JwtTokenUtil;
import com.opsigte.e.gateway.jwt.manager.impl.RedisTokenManager;
import com.opsigte.e.gateway.jwt.model.TokenModel;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> @ClassName: <i>EUserController</i></p>
 * <p> @Description: <i>用户服务接口提供类</i></p>
 * <p> @Author: <i>opsig</i></p>
 * <p> @Created date: <i>2019/8/16 23:15</i></p>
 * <p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@RestController
@RequestMapping("api/user")
public class EUserController {

    @Reference(version = "1.0.0", check = false)
    private EUserService userService;
    @Reference(version = "1.0.0", check = false)
    private CacheService cacheService;
    @Autowired
    private RedisTokenManager manager;


    @ApiOperation(value = "todo",notes = "todo测试接口")
    @RequestMapping(value = "todo",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String todo (HttpServletRequest request,String name){
        System.out.println("name:" + name);
        return "todo返回2!!!!";
    }

    @PostMapping(value = "insertUser")
    public Object insertUser(@RequestBody EUserEntity userEntity, HttpServletRequest request, HttpServletResponse response) {
        if (userEntity == null) {
            return "参数错误";
        }

        int insert = userService.insert(userEntity);
        if (insert != 0) {
            return JSON.toJSONString("添加成功" + insert);
        }
        return "添加失败";

    }

    @Authorization
    @GetMapping(value = "getUserById")
    public Resp getUserById(Integer id){

        System.out.println("id:" +SnowFlakeIdWorker.getId() );
        EUserEntity eUserEntity = new EUserEntity();
        eUserEntity.setAccountId(1111);
        return Resp.success(eUserEntity);
    }

    @GetMapping(value = "getUserById/{id}/test/{name}")
    public String getUserById2(@PathVariable("id") Integer id,@PathVariable("name") String name){
        EUserEntity userById = userService.getUserById(id);
        return JSON.toJSONString(userById);
    }


    @PostMapping(value = "login")
    public Resp login(String name,String password){
        log.info("name:{}", name);
        log.info("password:{}", password);
        if ("admin".equals(name) && "admin".equals(password)) {
            TokenModel tokenModel = manager.createTokenModel(name, null, JwtInfoConstant.JWT_TOKEN_EXPIRATION);
            return Resp.success(tokenModel);
        } else {
            return Resp.fail("login account is fail");
        }
    }



}
