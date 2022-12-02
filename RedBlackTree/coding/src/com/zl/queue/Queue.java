package com.zl.queue;

public interface Queue<T> {
    // 向队列中插入元素
    public void enqueue(T t);
    // 从队列中拿出元素
    public T dequeue();
    // 返回队列内元素个数
    public int size();
    // 查看队列是否为空
    public boolean isEmpty();
}
