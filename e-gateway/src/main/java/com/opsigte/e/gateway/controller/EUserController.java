package com.opsigte.e.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.opsigte.e.user.api.UserService;
import com.opsigte.e.user.api.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class EUserController {

    @Reference(version = "1.0.0", check = false)
    private UserService userService;

    @RequestMapping(value = "todo")
    public String todo (){
        return "todo";
    }

    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public String insertUser(@RequestBody UserEntity userEntity) {
        if (userEntity == null) {
            return "参数错误";
        }

        Integer insert = userService.insert(userEntity);
        if (insert != 0) {
            return "添加成功" + insert;
        }
        return "添加失败";

    }



}
