package com.zl.list.impl;

import com.zl.list.List;

public class BothwayLinkedList<T> implements List<T> {
    // 记录元素个数
    private int size = 0;
    // 记录头节点
    private Node head;
    // 记录尾节点
    private Node tail;
    // 节点内部类
    private class Node {
        // 节点值
        T v;
        // 上一个节点地址
        Node prev;
        // 下一个节点地址
        Node next;
        // 构造方法
        public Node(T v,Node prev,Node next) {
            this.v = v;
            this.prev = prev;
            this.next = next;
        }
    }


    @Override
    public void add(int index, T value) {
        if (index < 0 | index > size) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (head == null) {
            head = new Node(value,null,null);
            tail = head;
            size++;
            return;
        }
        if (index == 0) {
            head = new Node(value,head.prev,head);
            head.next.prev = head;
            size++;
            return;
        }
        if (index==size) {
            tail = new Node(value,tail,tail.next);
            tail.prev.next = tail;
            size++;
            return;
        }
        Node indexNode = findNodeOfIndex(index);
        Node newNode = new Node(value,indexNode.prev,indexNode);
        indexNode.prev.next = newNode;
        indexNode.prev = newNode;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        if (head == null) {
            return null;
        }
        size--;
        if (index == 0) {
            T r = head.v;
            head = head.next;
            head.prev = null;
            return r;
        }
        if (index == size-1) {
            T r = tail.v;
            tail = tail.prev;
            tail.next = null;
            return r;
        }
        Node indexNode = findNodeOfIndex(index);
        indexNode.prev.next = indexNode.next;
        indexNode.next.prev = indexNode.prev;
        return indexNode.v;
    }

    @Override
    public void set(int index, T value) {
        checkIndex(index);
        findNodeOfIndex(index).v = value;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
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

    // 根据index找节点
    private Node findNodeOfIndex(int index) {
        Node node = index >= (size >> 1) ? tail : head;
        if (index >= (size >> 1)) {
            for (int i = size-1; i > index; i--) {
                node = node.prev;
            }
        } else {
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        return node;
    }

    // 判断下标合法性
    private void checkIndex(int index) {
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
        return "size:" + size + "|" + "head:" + head.v + "|" + "tail:" + tail.v + "|" + tmp;
    }
}
