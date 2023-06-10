package org.javadegree.producerconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.javadegree.KafkaManager;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * La classe qui lit le canal Celsius est produit des données sur le canal Fahrenheit
 */
public class ProducerConsumer implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaProducer<String, String> producer;
    private final KafkaConsumer<String, String> consumer;
    private final KafkaStreams streams = KafkaManager.getKafkaStreams();

    /**
     *
     * @param producer un producteur Kafka
     * @param consumer un consomeur Kafka
     */
    public ProducerConsumer(KafkaProducer<String, String> producer, KafkaConsumer<String, String> consumer) {

        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        String topic_src = "Temperature-Celsius";
        String topic_dest = "Temperature-Fahrenheit";

        this.streams.start();

        try {
            consumer.subscribe(List.of(topic_src));
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                for (ConsumerRecord<String, String> record : records) {
                    int cel = Integer.parseInt(record.value());
                    int far = (cel * 9 / 5) + 32;

                    this.producer.send(new ProducerRecord<String, String>(topic_dest, "fahrenheit", Integer.toString(far)));

                    this.producer.flush();

                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
            this.producer.close();
        }
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
