package com.interview.study;

public class TransferMoneyService implements TransferMoney{
    @Override
    public void transfer(Account from, Account to, Double amount) {
        if (from.getName().equals(to.getName())) {
            throw  new IllegalArgumentException("Cant transfer the same account");
        }
        Account firstLock;
        Account secondLock;
        if (from.getName().compareTo(to.getName())<0) {
            firstLock = from;
            secondLock = to;
        } else {
            firstLock = to;
            secondLock = from;
        }
        try {
            firstLock.getLock().lock();
            try {
                secondLock.getLock().lock();
                from.withdraw(amount);
                to.deposit(amount);
            }finally {
                secondLock.getLock().unlock();
            }
        } finally {
            firstLock.getLock().unlock();
        }

    }

}
