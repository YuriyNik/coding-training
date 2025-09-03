package org.codetraining.stackqueue;

public interface Stack<T>{
    void push(T item);
    T pop();
    boolean isEmpty();
}
