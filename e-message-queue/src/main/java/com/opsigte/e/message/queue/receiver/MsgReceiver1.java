package com.opsigte.e.message.queue.receiver;

import com.opsigte.e.message.queue.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

/**
 *<p> @ClassName: <i>MsgReceiver1</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 20:26</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Component
@Slf4j
public class MsgReceiver1 {

   /* @RabbitHandler
    @RabbitListener(queues = RabbitMqConstant.QUEUE_1)
    public void receiver(String content){
        log.info("消费者1接收到的消息：{}", content);
    }*/

   /* @RabbitHandler
    public void receiver2(String content){
        log.info("消费者2接收到的消息：{}", content);
    }*/

}
