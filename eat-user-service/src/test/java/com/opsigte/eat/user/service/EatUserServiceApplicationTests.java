package com.opsigte.eat.user.service;

import com.opsigte.eat.common.core.page.PageParam;
import com.opsigte.eat.user.api.UserService;
import com.opsigte.eat.user.api.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EatUserServiceApplicationTests {

    @Autowired
    private UserService userService;

    @Resource
    private DataSource dataSource;


    @Test
    public void contextLoads() {
    }


    @Test
    public void testGetUserBy(){
        userService.getUserByUid("1");
    }

    @Test
    public void testFindUserAll(){
        userService.getAllUser();
    }

    @Test
    public void testDatasource(){
        System.out.println(this.dataSource);
    }

    @Test
    public void testPage(){

        Map<String, Object> map = new HashMap<>();
//        map.put("sort", "asc");
        map.put("sort", 2);

        PageParam pageParam = new PageParam(0,3);
        userService.listByPage(pageParam,map);
    }

    @Test
    public void testUpdate(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("test3333");
        userEntity.setId(1);
        System.out.println(userService.update(userEntity));
    }
}
