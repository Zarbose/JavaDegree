package org.javadegree.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consomeur implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;
    public Consomeur(KafkaConsumer<String, String> consumer){
        this.consumer = consumer;
    }

    @Override
    public void run() {
        String topic = "Temperature-Fahrenheit";
        try {
            consumer.subscribe(List.of(topic));
            while (!closed.get()) {
                // System.out.println("Avant");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                // System.out.println("Consomer: "+records.count());
                for (ConsumerRecord<String, String> record : records) {
                    // System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
