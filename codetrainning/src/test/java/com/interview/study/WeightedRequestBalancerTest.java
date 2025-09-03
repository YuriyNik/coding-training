package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WeightedRequestBalancerTest {

    @Test
    void weightedBalancerTest() {
        List<WeightedServer> servers = List.of(
            new WeightedServer("url1", 5 ),
            new WeightedServer("url2", 3 ),
            new WeightedServer("url3", 2 )
        );

        WeightedRequestBalancer weightedRequestBalancer = new WeightedRequestBalancer(servers);
        // Simulate multiple requests
        Map<String, Integer> distribution = new java.util.HashMap<>();
        for (int i = 0; i < 200; i++) {
            String server = weightedRequestBalancer.getNextServer();
            distribution.merge(server, 1, Integer::sum);
            System.out.println("Request " + (i + 1) + " assigned to " + server);
        }

        System.out.println("Distribution: " + distribution);
    }

    @Test
    void weightedBalancerNegativeTest_Null(){
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new WeightedRequestBalancer(null)
                );
        assertEquals("Server list cannot be empty", ex.getMessage());
    }

    @Test
    void weightedBalancerNegativeTest_EmptyList(){
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new WeightedRequestBalancer(List.of())
        );
        assertEquals("Server list cannot be empty", ex.getMessage());
    }

//    @Test
//    void weightedBalancerNegativeTest_brokenConfig(){
//        Exception ex = assertThrows(IllegalArgumentException.class,
//                () -> new WeightedRequestBalancer(List.of(new WeightedServer("url1",0),
//                        new WeightedServer("url2", 1 ),
//                        new WeightedServer("url3",0))).getNextServer()
//        );
//        assertEquals("Unexpected state: no server selected", ex.getMessage());
//    }

}