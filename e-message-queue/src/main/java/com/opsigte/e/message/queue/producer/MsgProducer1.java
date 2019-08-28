package com.opsigte.e.message.queue.producer;

import com.alibaba.dubbo.config.annotation.Service;
import com.opsigte.e.common.core.utils.UUIDUtil;
import com.opsigte.e.message.queue.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *<p> @ClassName: <i>MsgProducer1</i></p>
 *<p> @Description: <i>rabbitmq 生产者1</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 18:10</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Slf4j
@Service(version = "1.0.0",filter = "traceIdFilter")
public class MsgProducer1 implements RabbitTemplate.ConfirmCallback{

    /**
     * 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
     */
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MsgProducer1(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg(String content){
        CorrelationData correlationData = new CorrelationData(UUIDUtil.generatorUUID());
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_1,RabbitMqConstant.ROUTINGKEY_1,content,correlationData);
    }

    public void sendMsg2(String content){
        CorrelationData correlationData = new CorrelationData(UUIDUtil.generatorUUID());
        rabbitTemplate.convertAndSend(RabbitMqConstant.EXCHANGE_2,RabbitMqConstant.ROUTINGKEY_2,content,correlationData);
    }


    /**
     * 此回调必须在 publisherConfirms = true 的前提下才会执行
     *
     * @Title confirm
     * @param [correlationData, ack, cause]
     * @return void
     * @throws
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调ID：{}", correlationData);
        if (ack) {
            log.info("消息成功消费");
        } else {
            log.info("消息消费失败:{}", cause);
        }
    }
}
