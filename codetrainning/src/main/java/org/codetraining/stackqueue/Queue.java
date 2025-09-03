package org.codetraining.stackqueue;

public interface Queue<T> {
    void enqueue(T item);
    T dequeue();
    boolean isEmpty();
}
