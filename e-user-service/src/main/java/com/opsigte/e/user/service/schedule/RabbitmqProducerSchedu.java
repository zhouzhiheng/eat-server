package com.opsigte.e.user.service.schedule;

import com.opsigte.e.user.service.rabbitmq.producer.MsgProducer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *<p> @ClassName: <i>RabbitmqProducerSchedu</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/21 15:33</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Component
public class RabbitmqProducerSchedu {

    @Autowired
    private MsgProducer1 producer1;

    @Scheduled(fixedRate = 1000 * 10)
    public void producerMsg(){
        for (int i = 0; i < 5; i++) {

            producer1.sendMsg("定时发送的数据：" + i);

        }
    }

    @Scheduled(fixedRate = 1000 * 10)
    public void producerMsg2(){
        for (int i = 0; i < 5; i++) {

            producer1.sendMsg2("定时发送的数据：" + i);

        }
    }

}
