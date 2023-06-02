package org.javadegree;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.javadegree.consumer.Consomeur;
import org.javadegree.producer.Producteur;
import org.javadegree.producerconsumer.ProducerConsumer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        int min = -20;
        int max = 20;


        int nb_producer = 1;
        int nb_consumer = 1;
        int nb_producer_consumer = 1;

        System.out.println("OK");


        Properties props_cons = new Properties();
        props_cons.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        props_cons.setProperty("group.id", "test");
        props_cons.setProperty("enable.auto.commit", "true");
        props_cons.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props_cons.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> cons = new KafkaConsumer<>(props_cons);



        Properties props_pro = new Properties();
        props_pro.put("bootstrap.servers", "localhost:9094");
        // props.put("bootstrap.servers", "kafka:9092");
        // props.put("linger.ms", 1);
        props_pro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props_pro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> pro = new KafkaProducer<>(props_pro);



        
        // Producers
        /**/
        Thread[] producers = new Thread[nb_producer];
        for (int i = 0; i < nb_producer; i++){
            producers[i] = new Thread(new Producteur(min,max, pro));
            producers[i].start();
        }
        /**/

        TimeUnit.SECONDS.sleep(10);

        // Consumers
        /**/
        Thread[] consumers = new Thread[nb_consumer];
        for (int i = 0; i < nb_consumer; i++){
            consumers[i] = new Thread(new Consomeur(cons));
            consumers[i].start();
        }
        /**/

        // Producers Consumers
        /**/
        Thread[] producers_consumers = new Thread[nb_producer_consumer];
        for (int i = 0; i < nb_producer_consumer; i++){
            producers_consumers[i] = new Thread(new ProducerConsumer(pro, cons));
            producers_consumers[i].start();
        }
        /**/
    }
}
