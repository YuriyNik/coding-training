package com.interview.study;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LoadBalancer {
    public enum Strategy {
        RANDOM,
        ROUND_ROBIN
    }

    private static final int MAX_SERVERS = 10;
    private final List<String> servers = new ArrayList<>();
    private final ReentrantReadWriteLock rRWlock = new ReentrantReadWriteLock();
    private final AtomicInteger roundRobinIndex = new AtomicInteger(0);
    private final Random random = new Random();

    public void register(String address) {
        rRWlock.writeLock().lock();
        try{
        if (servers.size()>=(MAX_SERVERS)){
            throw new IllegalStateException("no more servers can be registered, limit "+MAX_SERVERS+" reached");
        }
        if (servers.contains(address)) {
            throw new IllegalArgumentException("Server "+address+" already registered");
        }
        servers.add(address);
        } finally {
            rRWlock.writeLock().unlock();
        }

    }

    public String get(Strategy strategy) {
            rRWlock.readLock().lock();
            try {
                if (servers.isEmpty()) {
                    throw new IllegalStateException("No servers reqistered");
                }
                switch (strategy) {
                    case RANDOM -> {
                        return servers.get(random.nextInt(servers.size()));
                    }
                    case ROUND_ROBIN -> {
//                            int rrIndex = roundRobinIndex.getAndIncrement();
                        int rrIndex = roundRobinIndex.getAndUpdate(i -> (i+1) % servers.size());
                            return servers.get(rrIndex % servers.size());
                    }
                    default -> throw new UnsupportedOperationException("Strategy " + strategy + " not supported");
                }
            } finally {
                rRWlock.readLock().unlock();
            }
    }

    public int size() {
        rRWlock.readLock().lock();
        try {
            return servers.size();
        }finally {
            rRWlock.readLock().unlock();
        }
    }
}
