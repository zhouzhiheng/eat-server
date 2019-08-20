package com.opsigte.e.user.service.rabbitmq.configuration;

import com.opsigte.e.user.service.rabbitmq.RabbitMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 *<p> @ClassName: <i>RabbitMqConfig</i></p>
 *<p> @Description: <i>rabbitmq消息队列配置类</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 17:46</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Configuration
public class RabbitMqConfig {

    private  int a  = 0;


    /**
     Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
     Queue:消息的载体,每个消息都会被投到一个或多个队列。
     Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
     Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
     vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
     Producer:消息生产者,就是投递消息的程序.
     Consumer:消息消费者,就是接受消息的程序.
     Channel:消息通道,在客户端的每个连接里,可建立多个channel.
     */

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory(host,port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost("/");
        /**
         * 1.对于每一个RabbitTemplate只支持一个ReturnCallback。
         * 对于返回消息，模板的mandatory属性必须被设定为true，
         * 它同样要求CachingConnectionFactory的publisherReturns属性被设定为true。
         * 如果客户端通过调用setReturnCallback(ReturnCallback callback)注册了RabbitTemplate.ReturnCallback，那么返回将被发送到客户端。
         * 这个回调函数必须实现下列方法：
         * void returnedMessage(Message message, intreplyCode, String replyText,String exchange, String routingKey);
         */
        // factory.setPublisherReturns(true);
        /**
         * 2.同样一个RabbitTemplate只支持一个ConfirmCallback。
         * 对于发布确认，template要求CachingConnectionFactory的publisherConfirms属性设置为true。
         * 如果客户端通过setConfirmCallback(ConfirmCallback callback)注册了RabbitTemplate.ConfirmCallback，那么确认消息将被发送到客户端。
         * 这个回调函数必须实现以下方法：
         * void confirm(CorrelationData correlationData, booleanack);
         */
        factory.setPublisherConfirms(true);
        return factory;
    }

    /**
     *  Scope必须是 prototype类型
     *  如果需要在生产者需要消息发送后的回调，需要对rabbitTemplate设置ConfirmCallback对象，
     *  由于不同的生产者需要对应不同的ConfirmCallback，如果rabbitTemplate设置为单例bean，
     *  则所有的rabbitTemplate 实际的ConfirmCallback为最后一次申明的ConfirmCallback。
     * @return
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }


    /**
     *  针对消费者配置
     *   * 1. 设置交换机类型
     *   FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
     *   HeadersExchange ：通过添加属性key-value匹配（不常用）
     *   DirectExchange:按照routingkey分发到指定队列
     *   TopicExchange:多关键字匹配
     *   * 2. 将指定队列绑定到交换机
     *
     * @return
     */
    /*@Bean
    public DirectExchange defaultExchange(){
        return new DirectExchange(RabbitMqConstant.EXCHANGE_1);
    }*/

    /*@Bean
    public Queue queue1(){
        // 第二个参数为true表示队列持久化
        return new Queue(RabbitMqConstant.QUEUE_1, true);
    }*/

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(new Queue(RabbitMqConstant.QUEUE_1, true))
            .to(new DirectExchange(RabbitMqConstant.EXCHANGE_1)).with(RabbitMqConstant.ROUTINGKEY_1);
    }
}
