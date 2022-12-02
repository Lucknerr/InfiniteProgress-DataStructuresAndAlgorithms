package com.zl.list.impl;

import com.zl.list.List;

public class SingleLinkedList<T> implements List<T> {
    private int size = 0;
    private Node head;
    private class Node {
        T v;
        Node next;
        public Node(T v,Node next) {
            this.v = v;
            this.next = next;
        }
    }

    @Override
    public void add(int index, T value) {
        if(index < 0 | index > size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        size++;
        if (index == 0) {
            head = new Node(value,head);
            return;
        }
        Node prev = findNodeOfIndex(index-1);
        prev.next = new Node(value,prev.next);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        size--;
        if (index == 0) {
            T r = head.v;
            head = head.next;
            return r;
        }
        Node prev = findNodeOfIndex(index-1);
        T r = prev.next.v;
        prev.next = prev.next.next;
        return r;
    }

    @Override
    public void set(int index, T value) {
        findNodeOfIndex(index).v = value;
    }

    @Override
    public T get(int index) {
        return findNodeOfIndex(index).v;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Node findNodeOfIndex(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public void checkIndex(int index) {
        if (index < 0 | index >= size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
    }

    // 测试呈现
    public String toString() {
        String tmp = "";
        Node node = head;
        for (int i = 0; i < size; i++) {
            tmp += "\t" + node.v + ";";
            node = node.next;
        }
        return tmp;
    }
}
