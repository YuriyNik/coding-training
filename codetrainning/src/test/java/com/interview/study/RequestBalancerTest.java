package com.interview.study;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RequestBalancerTest {
    @Test
    void balancerTest() {
        List<String> servers = List.of("Server1", "Server2", "Server3");
        RequestBalancer requestBalancer = new RequestBalancer(servers); // Assuming 3 servers
        // Simulate multiple requests
        for (int i = 0; i < 10; i++) {
            String server = requestBalancer.getNextServer();
            System.out.println("Request " + (i + 1) + " assigned to "+ server);
        }
    }

    @Test
    void balancerTest_Null(){
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new RequestBalancer(null)
        );
        assertEquals("Server list cannot be empty", ex.getMessage());
    }

    @Test
    void balancerTest_EmptyList(){
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new RequestBalancer(List.of())
        );
        assertEquals("Server list cannot be empty", ex.getMessage());
    }

}
