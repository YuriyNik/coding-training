package org.codetraining.concurrent;

import java.util.concurrent.*;

public class AsyncSamples {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> 2 * 2, pool);
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> 3 * 3, pool);
        CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> 4 * 4, pool);

        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);

        all.join(); // дождаться всех

        System.out.println("Результаты: " + f1.get() + ", " + f2.get() + ", " + f3.get());

        pool.shutdown();


        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            System.out.println("Поток: " + Thread.currentThread());
        });
        cf.join();


        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            executor.submit(() -> {
                System.out.println("Виртуальный поток: " + Thread.currentThread());
            });
        }


        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            StructuredTaskScope.Subtask<Integer> s1 = scope.fork(() -> 2 * 2);
            StructuredTaskScope.Subtask<Integer> s2 = scope.fork(() -> 3 * 3);

            scope.join();        // дождаться всех
            scope.throwIfFailed(); // пробросить исключения

            int result = s1.get() + s2.get();
            System.out.println("Сумма: " + result);
        }



    }



}
