package com.zl.list.impl;

import com.zl.list.List;

public class DynamicArray<T> implements List<T> {
    private int size = 0;
    private final static int INIT_LENGTH = 10;

    private int dilatationNums;
    private T[] array;

    public DynamicArray() {
        this(INIT_LENGTH);
    }

    public DynamicArray(int capacity) {
        this.array = (T[]) new Object[capacity > INIT_LENGTH ? capacity : INIT_LENGTH];
    }

    @Override
    public void add(int index, T value) {
        addCheckIndex(index);
        dilatation();
        insert(index,value);
    }

    public void endAdd(T value) {
        add(size,value);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T r = array[index];
        del(index);
        unDilatation();
        return r;
    }

    @Override
    public void set(int index, T value) {
        checkIndex(index);
        array[index] = value;
    }

    @Override
    public T get(int index) {
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

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index不合法");
        }
    }

    private void addCheckIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index不合法");
        }
    }
    private void dilatation() {
        if (size == array.length) {
            dilatationNums = array.length >> 1;
            int capacity = array.length + dilatationNums;
            copyArray(capacity);
        }
    }
    private void unDilatation() {
        if (array.length-size > dilatationNums) {
            int capacity = array.length - dilatationNums;
            copyArray(capacity);
        }
    }
    private void copyArray(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }
    private void insert(int index, T value) {
        for (int i = size; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = value;
        size++;
    }
    private void del(int index) {
        for (int i = index; i < size; i++) {
            array[i] = array[i+1];
        }
        array[size--] = null;
    }
    public String toString() {
        String eles = "";
        for (int i = 0; i < array.length;i++) {
            eles += "\t" + array[i] + ";";
        }
        return "size:"+ size + ";\nlength:" + array.length + ";\ndilatancyNms:" + dilatationNums + ";\neles:" + eles;
    }

}
