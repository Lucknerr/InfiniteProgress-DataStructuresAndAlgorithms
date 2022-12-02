package com.zl.stack;

import com.zl.list.List;
import com.zl.list.impl.DynamicArray;

// 栈接口
public interface Stack<T> {
    // 压栈
    public void push(T value);

    // 出栈
    public T pop();

    // 返回栈顶元素
    public T peek();

    // 测试呈现
    public String toString();
}
