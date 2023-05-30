package ProducerConsumer;

import java.util.concurrent.TimeUnit;

public class ProducerConsumer implements Runnable {
    private final int[] values;
    public ProducerConsumer(int[] values){
        this.values=values;
    }

    @Override
    public void run() {
        for (int cel: this.values) {
            // (0 °C × 9/5) + 32 = 32 °F
            int far = (cel*9/5)+32;
            System.out.println("Producer Consumer ("+Thread.currentThread().getId()+"): "+cel+" "+far);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
