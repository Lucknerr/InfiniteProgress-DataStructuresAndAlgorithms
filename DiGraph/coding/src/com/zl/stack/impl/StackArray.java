package com.zl.stack.impl;

import com.zl.list.List;
import com.zl.list.impl.DynamicArray;
import com.zl.stack.Stack;

public class StackArray<T> implements Stack<T> {

    List<T> list = new DynamicArray<>();

    @Override
    public void push(T value) {
        list.add(list.size(), value);
    }

    @Override
    public T pop() {
        return list.remove(list.size()-1);
    }

    @Override
    public T peek() {
        return list.get(list.size()-1);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public String toString() {
        return list.toString();
    }
}
