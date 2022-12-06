package com.zl.list.impl;

import com.zl.list.List;

public class DynamicArray<T> implements List<T> {
    private int size = 0;
    private static final int INIT_CAPACITY = 10;
    private int numDilatation = 0;
    private T[] array = (T[]) new Object[INIT_CAPACITY];

    public DynamicArray() {
        this(INIT_CAPACITY);
    }

    public DynamicArray(int capacity) {
        this.array = (T[]) new Object[capacity > INIT_CAPACITY ? capacity : INIT_CAPACITY];
    }

    @Override
    public void add(int index, T value) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (size == array.length) {
            dilatation();
        }
        for (int i = size; i > index ; i++) {
            array[i] = array[i-1];
        }
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T r = array[index];
        for (int i = index; i < size-1; i++) {
            array[i] = array[i+1];
        }
        size--;
        if (array.length-size > numDilatation) {
            deDilatation();
        }
        return r;
    }

    @Override
    public void set(int index, T value) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
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

    public void dilatation() {
        numDilatation = size >> 1;
        T[] newArray = (T[]) new Object[size+numDilatation];
        copyArray(newArray);
    }

    public void deDilatation() {
        T[] newArray = (T[]) new Object[array.length-numDilatation];
        copyArray(newArray);
    }
    public void copyArray(T[] newArray) {
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public void checkIndex(int index) {
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }

    // 测试呈现
    @Override
    public String toString() {
        String eles = "";
        for (int i = 0; i < array.length;i++) {
            eles += "\t" + array[i] + ";";
        }
        return "size:"+ size + "|\tlength:" + array.length + "|\tnumDilatation:" + numDilatation + "|\teles:" + eles;
    }
}
