package com.cobee.kafkademo.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.utils.CollectionUtils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Properties;

/**
 * Created by Administrator on 2018/11/11.
 */
public class ConsumerDemo {

    public static final String SERVER = "kafka-single:9092";
    public static final String TOPIC = "test";

    public static void main(String[] args) {
        Properties pros = new Properties();
        pros.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVER);
        pros.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        pros.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        pros.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "group-1");
        Consumer<String, Integer> consumer = new KafkaConsumer<String, Integer>(pros);
        consumer.subscribe(Arrays.asList(TOPIC));
        boolean flag = true;
        while(flag)
        {
            ConsumerRecords<String, Integer> consumerRecords = consumer.poll(Duration.ofMillis(200L));
            if (consumerRecords != null && !consumerRecords.isEmpty())
            {
                Iterator<ConsumerRecord<String, Integer>> iterable = consumerRecords.iterator();
                while(iterable.hasNext())
                {
                    ConsumerRecord<String, Integer> consumerRecord = iterable.next();
                    if (consumerRecord != null)
                    {
                        System.out.println("=========================key:" + consumerRecord.key() + ", value:" + consumerRecord.value());
                    }
                }
            }
        }
        consumer.close();
    }

}
