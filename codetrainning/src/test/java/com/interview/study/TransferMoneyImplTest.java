package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferMoneyImplTest {
    @Test
    void testMoneyTransfer(){
        Account from = new AccountImpl("FromGuy",100.0);
        Account to  = new AccountImpl("toGuy", 100.0);
        Double amount = 10.0;
        TransferMoney transferMoney = new TransferMoneyService();
        transferMoney.transfer(from,to,10.0);
        assertEquals(90.0,from.getBalance());
        assertEquals(110.0,to.getBalance());
    }

    @Test
    void testMoneyTransferConcurrent() throws InterruptedException {
        Account from = new AccountImpl("FromGuy",1000000.0);
        Account to  = new AccountImpl("ToGuy",0.0);
        TransferMoney transferMoney = new TransferMoneyService();

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Future<?>> tasks = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            tasks.add(executorService.submit(()-> transferMoney.transfer(from, to, 10.0)));
        }
        ExecutorUtils.waitAndCheckAll(tasks,executorService,1,TimeUnit.MILLISECONDS);
        System.out.println("from = "+from.getBalance());
        System.out.println("to = "+to.getBalance());
        assertEquals(0.0,from.getBalance());
        assertEquals(1000000.0,to.getBalance());
    }
}
