package org.javadegree.producerconsumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerConsumer implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaProducer<String, String> producer;
    private final KafkaConsumer<String, String> consumer;

    public ProducerConsumer(KafkaProducer<String, String> producer, KafkaConsumer<String, String> consumer){

        this.producer = producer;
        this.consumer = consumer;
    }

    /*@Override
    public void run() {

        String topic_src = "Temperature-Celsius";
        String topic_dest = "Temperature-Fahrenheit";

        try {
            this.consumer.subscribe(List.of(topic_src));
            while (!closed.get()) {

                System.out.println("Avant");
                ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(3000));
                System.out.println("Après: "+records.count());
                for (ConsumerRecord<String, String> record : records){

                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

                    int cel = Integer.parseInt(record.value());
                    int far = (cel*9/5)+32;

                    this.producer.send(new ProducerRecord<String, String>(topic_dest,"fahrenheit", Integer.toString(far)));

                    System.out.println("Send "+cel+" "+far);

                    this.producer.flush();
                }

            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            this.consumer.close();
            this.producer.close();
        }

        // (0 °C × 9/5) + 32 = 32 °F
        // int far = (cel*9/5)+32;
    }*/

    @Override
    public void run() {
        String topic_src = "Temperature-Celsius";
        String topic_dest = "Temperature-Fahrenheit";
        try {
            consumer.subscribe(List.of(topic_src));
            while (!closed.get()) {
                // System.out.println("Avant");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                // System.out.println("Après: "+records.count());
                for (ConsumerRecord<String, String> record : records) {
                    // System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());

                    int cel = Integer.parseInt(record.value());
                    int far = (cel*9/5)+32;

                    this.producer.send(new ProducerRecord<String, String>(topic_dest,"fahrenheit", Integer.toString(far)));

                    // System.out.println("Send "+cel+" "+far);

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
