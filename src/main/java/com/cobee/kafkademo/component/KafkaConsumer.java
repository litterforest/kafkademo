package com.cobee.kafkademo.component;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2018/11/11.
 */
@Component
public class KafkaConsumer {

//    @KafkaListener(topics = "test-concur")
//    public void processMessage(ConsumerRecord<String, Integer> consumerRecord) {
//        System.out.println(Thread.currentThread().getName() + "=========================key:" + consumerRecord.key() + ", value:" + consumerRecord.value());
//    }

    @KafkaListener(topics = "test-concur")
    public void processMessage(List<ConsumerRecord<String,Integer>> consumerRecordList) {

        if (!CollectionUtils.isEmpty(consumerRecordList))
        {
            for (ConsumerRecord<String,Integer> consumerRecord : consumerRecordList)
            {
                System.out.println(Thread.currentThread().getName() + "======================key:" + consumerRecord.key() + ", value:" + consumerRecord.value());
            }
        }

    }

    /*@KafkaListener(topics = "test-concur")
    public void processMessage(ConsumerRecords<String, Integer> consumerRecords) {

        if (consumerRecords != null && !consumerRecords.isEmpty())
        {
            Iterator<ConsumerRecord<String, Integer>> iterable = consumerRecords.iterator();
            while(iterable.hasNext())
            {
                ConsumerRecord<String, Integer> consumerRecord = iterable.next();
                if (consumerRecord != null)
                {
                    System.out.println(Thread.currentThread().getName() + "=========================key:" + consumerRecord.key() + ", value:" + consumerRecord.value());
                }
            }
        }

    }*/

}
