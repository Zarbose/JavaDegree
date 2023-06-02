package org.javadegree.producer;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Producteur implements Runnable {
    private final String topic = "Temperature-Celsius";
    private final int min;
    private final int max;
    private final KafkaProducer<String, String> producer;

    public Producteur(int min, int max){
        this.min=min;
        this.max=max;

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(props);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            int degree = (int) (this.min + (Math.random() * (this.max - this.min)));
            producer.send(new ProducerRecord<String, String>(topic, Integer.toString(degree)));

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        producer.close();
    }
}
