package org.javadegree.producerconsumer;

import org.apache.kafka.streams.KafkaStreams;

/**
 * La classe qui lit le canal Celsius est produit des données sur le canal Fahrenheit
 */
public class ProducerConsumer implements Runnable {
    private KafkaStreams streams;


    public ProducerConsumer(KafkaStreams stream) {
        this.streams = stream;
    }

    @Override
    public void run() {
        this.streams.start();
    }
}
