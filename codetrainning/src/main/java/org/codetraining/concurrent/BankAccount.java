package org.codetraining.concurrent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private int balance = 100;
    private final ReentrantLock lock = new ReentrantLock();

    public void withdraw(int amount) {
        lock.lock();
        try {
            if (balance >= amount) {
                System.out.println(Thread.currentThread().getName() + " снял " + amount);
                balance -= amount;
            } else {
                System.out.println(Thread.currentThread().getName() + " недостаточно средств");
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        BankAccount bankAccount = new BankAccount();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<Future<?>> tasks = new ArrayList<>();
//        List<Integer> amounts = Arrays.asList(1,2,3,3,1);
        for (int i = 0; i < 110; i++) {
//            for (int amount:amounts){
                tasks.add(executorService.submit( ()->bankAccount.withdraw(1) ));
//            }
        }
//        for (Future<?> task:tasks){
//            System.out.print(task.get());
//        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        System.out.println(bankAccount.balance);
    }
}
