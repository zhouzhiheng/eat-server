package com.opsigte.e.message.queue.receive.receive;

import com.opsigte.e.common.core.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>DirectReceiver</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>zzh</i></p>
 *<p> @Created date: <i>2019/9/16 13:37</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConstant.QUEUE_1,containerFactory = "jsonListenContainer")
public class DirectReceiver2 {

    @RabbitHandler
    public void receive(String msg){
        log.info("receive2接受到的消息：{}", msg);
    }

}
