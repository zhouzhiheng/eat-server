package com.opsigte.e.gateway.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;
import com.opsigte.e.common.core.response.Resp;
import com.opsigte.e.common.core.utils.UUIDUtil;
import com.opsigte.e.user.api.EUserService;
import com.opsigte.e.user.api.entity.EUserEntity;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

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



    @ApiOperation(value = "todo",notes = "todo测试接口")
    @RequestMapping(value = "todo",method = {RequestMethod.GET,RequestMethod.DELETE})
    public String todo (HttpServletRequest request,String name,@RequestBody EUserEntity userEntity){
        AsyncContext asyncContext = request.getAsyncContext();
        log.error("sss");
        log.info("zzz");
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
    public Resp getUserById(HttpServletRequest request,Integer id){
        String s = UUIDUtil.orderNo();
        RpcContext.getContext().setAttachment("traceId",s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        log.info("当前时间:{},tradeId:{}", sdf.format(new Date()),s);
        EUserEntity userById = userService.getUserById(id);
        return Resp.success(userById);
    }

    @GetMapping(value = "getUserById/{id}/test/{name}")
    public String getUserById2(@PathVariable("id") Integer id,@PathVariable("name") String name){
        EUserEntity userById = userService.getUserById(id);
        return JSON.toJSONString(userById);
    }




}
