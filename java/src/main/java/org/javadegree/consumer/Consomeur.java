package org.javadegree.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consomeur implements Runnable {
    private final String topic = "Temperature-Celsius";

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;
    public Consomeur(KafkaConsumer consumer){
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            consumer.subscribe(List.of(topic));
            while (!closed.get()) {
                // System.out.println("Avant");
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                System.out.println("Consomer: "+records.count());
                for (ConsumerRecord<String, String> record : records)
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                // System.out.println(records.);

            }
        } catch (WakeupException e) {
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
        // TimeUnit.SECONDS.sleep(3);
    }

    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}
