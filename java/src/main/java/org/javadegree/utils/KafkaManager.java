package org.javadegree.utils;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.javadegree.utils.Celsius;

import java.util.Properties;

/**
 * Cette classe permet de créé des producteurs et consommateurs Kafka
 */
public class KafkaManager {

    public KafkaManager() {

    }

    public KafkaProducer<String, String> getProducer() {
        Properties props_pro = new Properties();
        props_pro.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        // props.put("linger.ms", 1);
        props_pro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props_pro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> pro = new KafkaProducer<>(props_pro);

        return pro;
    }

    public KafkaConsumer<String, String> getConsumer() {
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

    public KafkaStreams getKafkaStreams(){
        String topic_src = "Temperature-Celsius";
        String topic_dest = "Temperature-Fahrenheit";


        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        //props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        StreamsBuilder builder = new StreamsBuilder();

        builder.<String, String>stream(topic_src).mapValues(value -> String.valueOf(new Celsius(value).toFahrenheit())).to(topic_dest);

        return new KafkaStreams(builder.build(), props);
    }
}
