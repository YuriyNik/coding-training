package com.interview.study;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestBalancer {
    private final List<String> servers;
    private final AtomicInteger counter = new AtomicInteger(0);

    public RequestBalancer(List<String> servers) {
        if (servers == null || servers.isEmpty()) {
            throw new IllegalArgumentException("Server list cannot be empty");
        }
        this.servers = servers;
    }

    public String getNextServer() {
        int index = Math.abs(counter.getAndIncrement() % servers.size());
        return servers.get(index);
    }

}

