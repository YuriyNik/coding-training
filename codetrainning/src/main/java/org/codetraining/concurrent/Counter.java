package org.codetraining.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {


    private int count = 0;
    public synchronized void increment() {
        count++; }
    public synchronized int getValue() { return count; }


    private AtomicInteger countAtomic = new AtomicInteger(0);
    public void incrementAtomic() { countAtomic.incrementAndGet(); }
    public int getValueAtomic() { return countAtomic.get(); }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Counter counter = new Counter();
        for (int i = 0; i < 100; i++) {
            new Thread(counter::increment).start();
            new Thread(counter::incrementAtomic).start();
        }
        Thread.sleep(100);
        System.out.println("Result = "+counter.getValue());
        System.out.println("Result = "+counter.getValueAtomic());


        ExecutorService pool = Executors.newFixedThreadPool(3);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Future<Integer>> futures = new ArrayList<>();
        for (int n : numbers) {
            futures.add(pool.submit(() -> n * n));
        }

        for (Future<Integer> f : futures) {
            System.out.println(f.get());
        }

        futures.add(pool.submit(() -> 10));
        pool.shutdown();

        System.out.println(futures.get(5).get());


    }


}
