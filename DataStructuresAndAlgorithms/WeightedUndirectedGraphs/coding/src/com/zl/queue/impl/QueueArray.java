package com.zl.queue.impl;

import com.zl.list.List;
import com.zl.list.impl.DynamicArray;
import com.zl.queue.Queue;

public class QueueArray<T> implements Queue<T> {
    List<T> list = new DynamicArray<>();

    @Override
    public void enqueue(T t) {
        list.add(list.size(),t);
    }

    @Override
    public T dequeue() {
        return list.remove(0);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 测试呈现
    @Override
    public String toString() {
        return list.toString();
    }

}
