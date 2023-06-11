package org.javadegree.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.concurrent.TimeUnit;

/**
 * Cette classe produit des données sur le topic Celsius
 */
public class Producteur implements Runnable {
    private final String topic = "Temperature-Celsius";
    private final int min;
    private final int max;
    private final KafkaProducer<String, String> producer;

    /**
     *
     * @param min la temperature minimal (Celsius)
     * @param max la temperature maximal (Celsius)
     * @param producer un producteur Kafka
     */
    public Producteur(int min, int max, KafkaProducer<String, String> producer) {
        this.min = min;
        this.max = max;

        this.producer = producer;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            int degree = (int) (this.min + (Math.random() * (this.max - this.min)));
            producer.send(new ProducerRecord<String, String>(topic, "celsius", Integer.toString(degree)));

            producer.flush();

            // Générer un nombre aléatoire entre 100 et 1500
            int randomDelay = 100 + (int) (Math.random() * (1500 - 100));

            try {
                TimeUnit.MILLISECONDS.sleep(randomDelay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        producer.close();
    }
}
