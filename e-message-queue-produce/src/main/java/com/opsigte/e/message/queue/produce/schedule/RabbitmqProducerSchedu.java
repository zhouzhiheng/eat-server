package com.opsigte.e.message.queue.produce.schedule;

import com.opsigte.e.message.queue.produce.producer.DirectProducer;
import com.opsigte.e.message.queue.produce.producer.FanoutProducer;
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
    private DirectProducer directProducer;
    @Autowired
    private FanoutProducer fanoutProducer;

    @Scheduled(fixedRate = 1000 * 5)
    public void fanoutProducerMsg(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fanoutProducer.sendMsg("fanout 发送的数据：" + sdf.format(new Date()));
    }

    /*@Scheduled(fixedRate = 1000 * 10)
    public void producerMsg2(){
        int i = 0;
        while (i < 1000) {
            i++;
            directProducer.sendMsg("direct 发送的数据：" + i);
        }
    }*/
}
