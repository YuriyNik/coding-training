package org.codetraining.stackqueue;

public class ArrayQueue<T> implements Queue<T>{

    private Object[] array;
    private int head;
    private int tail;
    private int size;
    private static final int DEFAULT_CAPACITY = 5;

    public ArrayQueue(){
        array = new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void enqueue(T item){
        if (size == array.length) {
            resize();
        }
        array[tail] = item;
        tail=(tail+1)%array.length;
        System.out.println("tail="+tail+";");
        size++;
    }
    @SuppressWarnings("unchecked")
    @Override
    public T dequeue(){
        T item = (T) array[head];
        array[head] = null;
        head = (head+1)%array.length;
        System.out.println("head="+head);
        size--;
        return item;
    }
    @Override
    public boolean isEmpty(){
        return size==0;
    }

    private void resize(){
        Object[] newArray = new Object[array.length*2];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % array.length];
        }
        array = newArray;
        head = 0;
        tail = size;

    }
}
