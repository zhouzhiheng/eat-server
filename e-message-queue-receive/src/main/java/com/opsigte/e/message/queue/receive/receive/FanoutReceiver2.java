package com.opsigte.e.message.queue.receive.receive;

import com.opsigte.e.common.core.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>FanoutReceiver</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/9/19 11:10</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConstant.FANOUT_QUEUE_2,containerFactory = "jsonListenContainer")
public class FanoutReceiver2 {


    @RabbitHandler
    public void receive(String msg){
        log.info("fanout2 接受到的消息：{}", msg);
    }
}
