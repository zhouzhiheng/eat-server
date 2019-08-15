package com.opsigte.e.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/user")
public class EUserController {

    @Reference(version = "1.0.0", check = false)
    private EUserService userService;

    @RequestMapping(value = "todo")
    public String todo (String name){
        System.out.println("name:" + name);
        return "todo返回";
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

    @GetMapping(value = "getUserById")
    public String getUserById(Integer id){
        EUserEntity userById = userService.getUserById(id);
        return JSON.toJSONString(userById);
    }

    @GetMapping(value = "getUserById/{id}")
    public String getUserById2(@PathVariable("id") Integer id){
        EUserEntity userById = userService.getUserById(id);
        return JSON.toJSONString(userById);
    }




}
