package com.interview.study;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class WeightedServer {
    String url;
    int weight;

    WeightedServer(String url, int weight) {
        this.url = url;
        this.weight = weight;
    }
}

public class WeightedRequestBalancer {
    private final List<WeightedServer> servers;
    private final AtomicInteger counter = new AtomicInteger(0);
    private final int totalWeight;

    public WeightedRequestBalancer(List<WeightedServer> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("Server list cannot be empty");
        }
        this.servers = servers;
        this.totalWeight = servers.stream().mapToInt(s -> s.weight).sum();
    }

    public String getNextServer() {
        int index = Math.abs(counter.getAndIncrement() % totalWeight);
        int sum = 0;
        for (WeightedServer server : servers) {
            sum += server.weight;
            System.out.println("looking for server: index=" + index + ", sum=" + sum + " for " + server.url);
            if (index < sum) {
                return server.url;
            }
        }
       throw new IllegalStateException("Unexpected state: no server selected");
    }

}
