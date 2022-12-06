package com.zl.stack.impl;

import com.zl.list.List;
import com.zl.list.impl.DynamicArray;
import com.zl.stack.Stack;

// 动态数栈
// 实现栈接口
public class StackArray<T> implements Stack<T> {
    // 创建动态数组关联关系
    List<T> list = new DynamicArray<T>();

    // 压栈
    @Override
    public void push(T value) {
        list.add(list.size(),value);
    }

    // 出栈
    @Override
    public T pop() {
        return list.remove(list.size()-1);
    }

    // 返回栈顶元素
    @Override
    public T peek() {
        return list.get(list.size()-1);
    }

    // 测试呈现
    @Override
    public String toString() {
        return list.toString();
    }
}
