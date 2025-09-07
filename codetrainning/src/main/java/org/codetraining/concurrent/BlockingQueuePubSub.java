package org.codetraining.concurrent;
import java.util.concurrent.*;

public class BlockingQueuePubSub {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);

        Runnable producer = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    queue.put(i);
                    System.out.println("Произведено: " + i);
                }
            } catch (InterruptedException ignored) {}
        };

        Runnable consumer = () -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    int val = queue.take();
                    System.out.println("Потреблено: " + val);
                }
            } catch (InterruptedException ignored) {}
        };
        new Thread(consumer).start();
        Thread.sleep(1000);
        new Thread(producer).start();
    }
}
