package com.opsigte.e.message.queue.producer;

import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.e.message.queue.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>FanoutProducer</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/28 20:40</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Service(version = "1.0.0",filter = "traceIdFilter")
public class FanoutProducer implements RabbitTemplate.ConfirmCallback {


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }


    public void sendMsg(String content){
        rabbitTemplate.convertAndSend(RabbitMqConstant.FANOUT_EXCHANGE_1, content);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        log.info("回调ID：{}", correlationData);
        if (b) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:{}", s);
        }
    }
}
