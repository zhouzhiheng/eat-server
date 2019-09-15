package com.opsigte.e.message.queue.receiver;

import com.opsigte.e.message.queue.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>FanoutReceiver</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/28 20:44</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConstant.FANOUT_QUEUE_2)
public class FanoutReceiver {


    @RabbitHandler
    public void msg(String content){
        log.info("fanout2接收到的消息：" + content);
    }
}
