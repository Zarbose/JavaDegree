import Producer.Producer;
import Consumer.Consumer;
import ProducerConsumer.ProducerConsumer;
public class Main {
    public static void main(String[] args) throws InterruptedException {

        int min = -20;
        int max = 20;

        int[] values = new int[100];

        for (int i = 0; i < values.length; i++){
            values[i] = (int) (min + (Math.random() * (max - min)));
        }

        int nb_producer = 10;
        int nb_consumer = 10;
        int nb_producer_consumer = 10;

        // Producers
        /**
        Thread[] producers = new Thread[nb_producer];
        for (int i = 0; i < nb_producer; i++){
            producers[i] = new Thread(new Producer(min,max));
            producers[i].start();
        }
        /**/

        // Consumers
        /**
        Thread[] consumers = new Thread[nb_consumer];
        for (int i = 0; i < nb_consumer; i++){
            consumers[i] = new Thread(new Consumer(values));
            consumers[i].start();
        }
        /**/

        // Producers Consumers
        /**
        Thread[] producers_consumers = new Thread[nb_producer_consumer];
        for (int i = 0; i < nb_producer_consumer; i++){
            producers_consumers[i] = new Thread(new ProducerConsumer(values));
            producers_consumers[i].start();
        }
        /**/
    }
}