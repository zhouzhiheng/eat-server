package com.opsigte.e.message.queue.produce.producer;

import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.e.common.core.utils.UUIDUtil;
import com.opsigte.e.message.queue.produce.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *<p> @ClassName: <i>DirectProducer</i></p>
 *<p> @Description: <i>rabbitmq 生产者1</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 18:10</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Service(version = "1.0.0",filter = "traceIdFilter")
public class DirectProducer implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

    /**
     * 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
     */
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DirectProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        // 如果想让 returnCallback回调,则必须设置Mandatory属性为true
        rabbitTemplate.setMandatory(true);
        // 设置以json的方式传递消息到exchange
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);

    }

    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUIDUtil.generatorUUID());
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_1,RabbitMqConstant.ROUTINGKEY_1,content,correlationData);
    }


    /**
     * 此回调必须在 connectionFactory.publisherConfirms = true 的前提下才会执行
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("direct 回调ID：{}", correlationData);
        if (ack) {
            log.info("direct 消息发送成功");
        } else {
            log.info("direct 消息发送失败:{}", cause);
        }
    }


    /**
     * 此回调必须在 connectionFactory.publisherReturns = true 的前提下才会执行
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("direct 消息丢失：exchange({}),routingKey({}),replyCode({}),replyText({})", exchange, routingKey,replyCode,replyText);
    }
}
