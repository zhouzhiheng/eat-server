package com.opsigte.e.user.service;

import com.opsigte.e.common.core.utils.PublicReadConfigUtil;
import com.opsigte.e.common.core.page.PageParam;
import com.opsigte.e.user.api.EUserService;
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
public class EUserServiceApplicationTests {

    @Autowired
    private EUserService EUserService;

    @Resource
    private DataSource dataSource;


    @Test
    public void contextLoads() {
    }


}
