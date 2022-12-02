package com.zl.list.impl;

import com.zl.list.List;

public class SingleLinkedList<T> implements List<T> {
    private int size;
    private Node head;
    private class Node {
        T value;
        Node next;
        public Node(T value,Node next) {
            this.value = value;
            this.next = next;

        }
    }

    public SingleLinkedList(T value) {
        this.head = new Node(value,null);
        size++;
    }
    @Override
    public void add(int index, T value) {
        addCheckIndex(index);
        insert(index,value);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        return del(index);
    }

    @Override
    public void set(int index, T value) {
        findOfIndex(index).value = value;
    }

    @Override
    public T get(int index) {
        return findOfIndex(index).value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void insert(int index, T value) {
        size++;
        if (index == 0) {
            head = new Node(value,head);
            return;
        }
        Node pre = findOfIndex(index-1);
        pre.next = new Node(value,pre.next);
    }

    private T del(int index) {
        size--;
        if (index == 0) {
            T r = head.value;
            head = head.next;
            return r;
        }
        Node pre = findOfIndex(index-1);
        T r = pre.next.value;
        pre.next = pre.next.next;
        return r;
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

    private Node findOfIndex(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public String toString() {
        String tmp = "";
        Node node = head;
        for (int i = 0; i < size; i++) {
            tmp += "\t" + node.value + ";";
            node = node.next;
        }
        return tmp;
    }

}
