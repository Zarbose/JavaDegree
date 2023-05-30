package org.javadegree.Producer;

import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {
    private final int min;
    private final int max;
    public Producer(int min, int max){
        this.min=min;
        this.max=max;
    }

    @Override
    public void run() {
        while (true){
            int degree = (int) (this.min + (Math.random() * (this.max - this.min)));
            System.out.println("Producer ("+Thread.currentThread().getId()+"): "+degree);

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
