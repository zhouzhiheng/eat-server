package com.opsigte.e.message.queue.produce.receiver;

import com.opsigte.e.message.queue.produce.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>MsgReceiver1</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 20:26</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMqConstant.QUEUE_1,containerFactory = "jsonListenContainer")
public class DirectReceiver {

    @RabbitHandler
    public void receiver(String content){
        log.info("direct消费者接收到的消息：{}", content);
    }

   /* @RabbitHandler
    public void receiver2(String content){
        log.info("消费者2接收到的消息：{}", content);
    }*/

}
