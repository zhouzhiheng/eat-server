package com.opsigte.e.message.queue.schedule;

import com.opsigte.e.message.queue.producer.FanoutProducer;
import com.opsigte.e.message.queue.producer.MsgProducer1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *<p> @ClassName: <i>RabbitmqProducerSchedue</i></p>
 *<p> @Description: <i></i></p>
 *<p> @Author: <i>opsigte</i></p>
 *<p> @Created date: <i>2019/8/21 15:33</i></p>
 *<p> @Version: <i>V1.0.0</i> </p>
 */
@Component
public class RabbitmqProducerSchedu {

    @Autowired
    private MsgProducer1 producer1;
    @Autowired
    private FanoutProducer fanoutProducer;

    /*@Scheduled(fixedRate = 1000 * 10)
    public void producerMsg(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        producer1.sendMsg("定时发送的数据：" + sdf.format(new Date()));
    }*/

    @Scheduled(fixedRate = 1000 * 10)
    public void fanoutProducerMsg(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        fanoutProducer.sendMsg("定时发送的数据：" + sdf.format(new Date()));
    }

    /*@Scheduled(fixedRate = 1000 * 10)
    public void producerMsg2(){
        for (int i = 0; i < 5; i++) {

            producer1.sendMsg2("定时发送的数据：" + i);

        }
    }*/

}
