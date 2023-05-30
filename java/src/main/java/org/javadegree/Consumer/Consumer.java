package org.javadegree.Consumer;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {
    private final int[] values;
    public  Consumer(int[] values){
        this.values=values;
    }

    @Override
    public void run() {
        for (int i: this.values) {
            System.out.println("Consumer ("+Thread.currentThread().getId()+"): "+i);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
