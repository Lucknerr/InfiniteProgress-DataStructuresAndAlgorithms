package com.zl.list.impl;

import com.zl.list.List;

// 动态数组
// 实现列表接口
public class DynamicArray<T> implements List<T> {
    // 记录元素个数
    private int size = 0;
    // 默认初始化容量
    private static final int INIT_CAPACITY = 10;
    // 记录扩容量
    private int dilatations;
    // 当元素个数为空时需要返回的值
    private static final int IS_NULL = -1;
    // 用于存储元素
    private T[] array = (T[]) new Object[INIT_CAPACITY];
    // 无参构造
    public DynamicArray(){
        this(INIT_CAPACITY);
    }
    // 有参构造函数
    public DynamicArray(int capacity){
        this.array = (T[]) new Object[capacity > INIT_CAPACITY ? capacity : INIT_CAPACITY];
    }

    // 指定位置添加元素
    @Override
    public void add(int index, T value) {
        // 对输入下标判断是否合法,如果不合法提出警告
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        // 判断数组是否已经存满,存满扩容
        if (size == array.length) {
            dilatation();
        }
        // 将index后所有元素从后往前一次向后移动一位
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        // index位置被赋予目标值
        array[index] = value;
        // 元素个数加一
        size++;
    }

    // 指定位置删除元素
    @Override
    public T remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException("已经空了");
        }
        // 对输入下标判断是否合法,如果不合法提出警告
        checkIndex(index);
        T remove = array[index];
        // 将index后所有元素从前往后一次向前移动一位
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
        size--;
        if (array.length-size > dilatations) {
            shrinkage();
        }
        return remove;
    }

    @Override
    public void set(int index, T value) {
        // 对输入下标判断是否合法,如果不合法提出警告
        checkIndex(index);
        // 直接这是值
        array[index] = value;
    }

    @Override
    public T get(int index) {
        // 对输入下标判断是否合法,如果不合法提出警告
        checkIndex(index);
        // 直接获取值
        return array[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    // 测试呈现
    @Override
    public String toString() {
        String eles = "";
        for (int i = 0; i < array.length;i++) {
            eles += "\t" + array[i] + ";";
        }
        return "size:"+ size + "|\tlength:" + array.length + "|\tdilatations:" + dilatations + "|\teles:" + eles;
    }
    // 数组扩容
    private void dilatation() {
        // 扩容量,原来的近1.5倍
        dilatations = array.length >> 1;
        // 创建长度为原来1.5倍的新数组
        T[] newArray = (T[]) new Object[array.length + dilatations];
        copyArray(newArray);
    }

    // 数组缩容
    private void shrinkage() {
        // 创建长度为原来2/3倍的新数组
        T[] newArray = (T[]) new Object[array.length - dilatations];
        // 将原来数组元素拷贝到新数组
        copyArray(newArray);
    }

    // 拷贝数组
    private void copyArray(T[] newArray) {
        // 将原来数组元素拷贝到新数组
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        // 新数组充当容器
        array = newArray;
    }

    // 对输入下标判断是否合法,如果不合法提出警告
    private void checkIndex(int index) {
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }
}
