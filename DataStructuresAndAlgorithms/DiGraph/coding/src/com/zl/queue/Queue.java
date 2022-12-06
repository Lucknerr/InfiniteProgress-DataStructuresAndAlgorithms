package com.zl.queue;

public interface Queue<T> {

    public void enqueue(T t);

    public T dequeue();

    public int size();

    public boolean isEmpty();

}
