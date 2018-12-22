package com.cobee.kafkademo.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * Created by Administrator on 2018/11/11.
 */
public class ProducerDemo {

    public static final String SERVERS = "kafka-single:9092";
    public static final String TOPIC = "test-concur";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SERVERS);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        Producer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
        {
            producer.send(new ProducerRecord<String, Integer>(TOPIC, "cobee-" + i, i));
        }
        long end = System.currentTimeMillis();
        producer.close();
        System.out.println("=============== 消费者发送完成，共花费:" + (end - start) + "ms");
    }

}
