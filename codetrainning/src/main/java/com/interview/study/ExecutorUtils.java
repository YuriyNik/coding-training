package com.interview.study;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutorUtils {
    public static void waitAndCheckAll(List<? extends Future<?>> futures,
                                       ExecutorService executor,
                                       long timeout,
                                       TimeUnit unit) {
        executor.shutdown();
        for (Future<?> task : futures) {
            try {
                task.get();
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException("Money tranfer failed" + e.getCause());
            }

        }
        try {
            if (!executor.awaitTermination(timeout, unit))
            {
                throw new RuntimeException("procedure didnt finish in time for "+timeout+" "+unit);
            } } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrupted while waiting for termination " + e.getCause());
        }


    }
}
