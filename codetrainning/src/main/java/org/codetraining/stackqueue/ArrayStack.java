package org.codetraining.stackqueue;

public class ArrayStack<T> implements Stack<T> {
    private Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 4;

    public ArrayStack(){
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public void push(T item) {
        if (size == array.length) {
            resize();
        }
        array[size++] = item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T pop() {
        if (isEmpty()){
            throw new IllegalStateException("stack is empty");
        }
        T item = (T)array[--size];
        array[size] = null;
        return item;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    private void resize(){
        Object[] newArray = new Object[array.length*2];
        System.arraycopy(array,0,newArray,0,array.length);
        array=newArray;

    }
}
