package org.javadegree;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Properties;

public class KafkaManager {

    public KafkaManager(){

    }

    public KafkaProducer<String, String> getProducer(){
        Properties props_pro = new Properties();
        props_pro.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        // props.put("linger.ms", 1);
        props_pro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props_pro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> pro = new KafkaProducer<>(props_pro);

        return pro;
    }

    public KafkaConsumer<String, String> getConsumer(){
        Properties props_cons = new Properties();
        props_cons.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        props_cons.setProperty("group.id", "test");
        props_cons.setProperty("enable.auto.commit", "true");
        props_cons.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props_cons.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> cons = new KafkaConsumer<>(props_cons);

        return cons;
    }
}
