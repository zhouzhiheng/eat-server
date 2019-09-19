package com.opsigte.e.message.queue.receive.configuration;

import com.opsigte.e.common.core.constant.RabbitMqConstant;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;


/**
 *<p> @ClassName: <i>RabbitMqConfig</i></p>
 *<p> @Description: <i>rabbitmq消息队列配置类.初始化创建队列、交换器，并把队列绑定到交换器</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 17:46</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Configuration
public class RabbitMqReceiveConfig {

    /**
     Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
     Exchange：消息交换器,它指定消息按什么规则,路由到哪个队列。
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

    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean pubConfirm;

    @Value("${spring.rabbitmq.publisher-returns}")
    private Boolean resConfirm;

    /**
     * 定义rabbitmq连接信息
     *
     * @Title connectionFactory
     * @param []
     * @return org.springframework.amqp.rabbit.connection.ConnectionFactory
     * @throws
     */
    @Bean
    @Primary
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory factory = new CachingConnectionFactory(host,port);
        factory.setUsername(username);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
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
     * 定义接收者使用什么方式处理接受到的消息（此方法使用json处理,需要在receiver方指定containerFactory = "jsonListenContainer"）
     *
     * @Title rabbitListenerContainerFactory
     * @param []
     * @return org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
     * @throws
     */
    @Bean(name="jsonListenContainer")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setConnectionFactory(connectionFactory());
        return factory;
    }


}

