package com.interview.study;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountImpl implements Account{
    private Double balance=0.0;
    private String name;

    private final Lock lock = new ReentrantLock();

    @Override
    public Lock getLock() {
        return lock;
    }

    public AccountImpl(String name){
        this.name=name;
    }

    public AccountImpl(String name, Double balance){
        this.name=name;
        this.balance=balance;
    }

    @Override
    public Double getBalance(){
      return balance;
    }
    @Override
    public void deposit(Double amount) {
        this.balance += amount;
    }
    @Override
    public void withdraw(Double amount) {
        if (balance<amount) {
            throw  new IllegalArgumentException("not enough money on the account");
        }
        this.balance -= amount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
