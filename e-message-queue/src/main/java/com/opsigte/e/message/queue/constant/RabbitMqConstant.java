package com.opsigte.e.message.queue.constant;

/**
 *<p> @ClassName: <i>RabbitMqConstat</i></p>
 *<p> @Description: <i>rabbitmq 常量类，存放交换器以及路由key</i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/20 18:25</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
public class RabbitMqConstant {

    public static final String EXCHANGE_1 = "my-mq-exchange_1";
    public static final String EXCHANGE_2 = "my-mq-exchange_2";
    public static final String TOPIC_EXCHANGE_1 = "my-topic-exchange_1";
    public static final String FANOUT_EXCHANGE_1 = "my-fanout-exchange_1";
    public static final String FANOUT_EXCHANGE_2 = "my-fanout-exchange_2";


    public static final String ROUTINGKEY_1 = "spring-boot-routingKey_1";
    public static final String ROUTINGKEY_2 = "spring-boot-routingKey_2";
    public static final String TOPIC_ROUTINGKEY_1 = "TOPIC_ROUTINGKEY_1";
    public static final String FANOUT_ROUTINGKEY_1 = "FANOUT_ROUTINGKEY_1";


    public static final String QUEUE_1 = "QUEUE_1";
    public static final String QUEUE_2 = "QUEUE_2";
    public static final String TOPIC_QUEUE_1 = "TOPIC_QUEUE_1";
    public static final String FANOUT_QUEUE_1 = "FANOUT_QUEUE_1";
    public static final String FANOUT_QUEUE_2 = "FANOUT_QUEUE_2";
    public static final String FANOUT_QUEUE_3 = "FANOUT_QUEUE_3";


}
