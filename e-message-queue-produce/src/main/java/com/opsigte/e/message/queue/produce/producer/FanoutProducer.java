package com.opsigte.e.message.queue.produce.producer;

import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.e.common.core.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *<p> @ClassName: <i>FanoutProducer</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/28 20:40</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Service(version = "1.0.0",filter = "traceIdFilter")
public class FanoutProducer implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public FanoutProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }


    public void sendMsg(String content){
        // fanout exchange会忽略 routingKey参数.
        rabbitTemplate.convertAndSend(RabbitMqConstant.FANOUT_EXCHANGE_2, "",content);
    }




    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        /**
         * exchange到queue成功,则不回调return
         * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
         */
        log.info("消息丢失：exchange({}),routingKey({}),replyCode({}),replyText({})", exchange, routingKey,replyCode,replyText);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        /**
         * 如果消息没有到exchange,则confirm回调,ack=false
         * 如果消息到达exchange,则confirm回调,ack=true
         */
        log.info("回调ID：{}", correlationData);
        if (ack) {
            log.info("消息发送成功");
        } else {
            log.info("消息发送失败:{}", cause);
        }
    }
}
