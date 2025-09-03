package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class LoadBalancerTest {


    @Test
    void concurrentGetShouldNotThrow() throws InterruptedException, ExecutionException {
        LoadBalancer lb = new LoadBalancer();
        lb.register("srv1");
        lb.register("srv2");
        lb.register("srv3");

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            tasks.add(()->lb.get(LoadBalancer.Strategy.RANDOM));
        }
        List<Future<String>> results = executorService.invokeAll(tasks);
        executorService.shutdown();
        executorService.awaitTermination(1,TimeUnit.SECONDS);

        for (Future<String> result:results){
            String value = result.get();
            assertTrue(value.equals("srv1")|value.equals("srv2")|value.equals("srv3"));
        }

    }

    @Test
    void concurrentRegisterShouldRespectLimitAndNoDuplicates() throws InterruptedException {
        LoadBalancer lb = new LoadBalancer();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Callable<Void>> tasks = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            final String server = "srv"+i;
            tasks.add(() -> {
                try {
                    lb.register(server);
                } catch (IllegalArgumentException | IllegalStateException ignore) {
                    System.out.println("duplications exceptions for " + server);
                }
                return null;
            }
            );
        }

        executorService.invokeAll(tasks);
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.SECONDS);

        assertTrue(lb.size()<=10);


    }


    @Test
    void registerAndSize() {
        LoadBalancer lb = new LoadBalancer();
        lb.register("server1");
        lb.register("server2");
        assertEquals(2, lb.size());
    }

    @Test
    void registerDuplicateShouldThrow() {
        LoadBalancer lb = new LoadBalancer();
        lb.register("server1");
        assertThrows(IllegalArgumentException.class, () -> lb.register("server1"));
    }

    @Test
    void registerMoreThanLimitShouldThrow() {
        LoadBalancer lb = new LoadBalancer();
        for (int i = 1; i <= 10; i++) {
            lb.register("server" + i);
        }
        assertThrows(IllegalStateException.class, () -> lb.register("server11"));
    }

    @Test
    void getRandom(){
        LoadBalancer lb = new LoadBalancer();
        lb.register("srv1");
        lb.register("srv2");
        String result = lb.get(LoadBalancer.Strategy.RANDOM);
        assertTrue(result.equals("srv1") ||result.equals("srv2"));
    }

    @Test
    void getRoundRobin(){
        LoadBalancer lb = new LoadBalancer();
        lb.register("srv1");
        lb.register("srv2");
        lb.register("srv3");
        lb.register("srv4");
        assertEquals("srv1",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
        assertEquals("srv2",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
        assertEquals("srv3",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
        assertEquals("srv4",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
        assertEquals("srv1",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
        assertEquals("srv2",lb.get(LoadBalancer.Strategy.ROUND_ROBIN));
    }


}
