package com.interview.study;

import java.util.concurrent.locks.Lock;

public interface Account {
    Double getBalance();
    void deposit(Double amount);
    void withdraw(Double amount);
    Lock getLock();
    String getName();
    void setName(String name);
}
