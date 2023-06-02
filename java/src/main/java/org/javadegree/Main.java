package org.javadegree;

import org.javadegree.consumer.Consomeur;
import org.javadegree.producer.Producteur;
import org.javadegree.producerconsumer.ProducerConsumer;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int min = -20;
        int max = 20;


        int nb_producer = 1;
        int nb_consumer = 1;
        int nb_producer_consumer = 1;


        KafkaManager manager = new KafkaManager();
        
        // Producers
        /**/
        Thread[] producers = new Thread[nb_producer];
        for (int i = 0; i < nb_producer; i++){
            producers[i] = new Thread(new Producteur(min,max, manager.getProducer()));
            producers[i].start();
        }
        /**/

        TimeUnit.SECONDS.sleep(5);

        // Consumers
        /**/
        Thread[] consumers = new Thread[nb_consumer];
        for (int i = 0; i < nb_consumer; i++){
            consumers[i] = new Thread(new Consomeur(manager.getConsumer()));
            consumers[i].start();
        }
        /**/

        // Producers Consumers
        /**/
        Thread[] producers_consumers = new Thread[nb_producer_consumer];
        for (int i = 0; i < nb_producer_consumer; i++){
            producers_consumers[i] = new Thread(new ProducerConsumer(manager.getProducer(), manager.getConsumer()));
            producers_consumers[i].start();
        }
        /**/
    }
}
