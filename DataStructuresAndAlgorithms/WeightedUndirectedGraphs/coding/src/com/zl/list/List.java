package com.zl.list;

// 列表接口
public interface List<T> {
    // 指定位置添加元素
    public void add(int index,T value);
    // 删除指定元素
    public T remove(int index);
    // 设置指定元素值
    public void set(int index,T value);
    // 获取指定元素值
    public T get(int index);
    // 是否为空
    public boolean isEmpty();
    // 获取元素个数
    public int size();
}
