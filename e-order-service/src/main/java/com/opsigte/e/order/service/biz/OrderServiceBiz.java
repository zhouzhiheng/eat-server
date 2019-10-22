package com.opsigte.e.order.service.biz;

import com.opsigte.e.order.api.entity.OrderEntity;
import com.opsigte.e.order.service.mapper.EOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *<p> @ClassName: <i>OrderServiceBiz</i></p>
 *<p> @Description: <i>订单服务具体业务类</i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/10/18 10:31</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Service
public class OrderServiceBiz {

    @Autowired
    private EOrderMapper orderMapper;


    public int insertOrder(OrderEntity orderEntity) {

        return 1;
    }



}
