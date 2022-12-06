package com.zl.list.impl;

import com.zl.list.List;

// 单向链表
// 实现列表接口
public class SingleLinkedList<T> implements List<T> {
    // 记录节点个数
    private int size = 0;
    // 存放头节点
    private Node head;
    // 节点类
    private class Node {
        // 节点值
        T value;
        // 下一个节点地址
        Node next;
        // 构造方法
        public Node(T value,Node next) {
            this.value = value;
            this.next = next;
        }
    }

    // 插入元素到指定节点
    @Override
    public void add(int index, T value) {
        if (index < 0 | index > size ) {
            throw new IndexOutOfBoundsException("下标不合法");
        }
        if (index == 0) {
            head = new Node(value,head);
            size++;
            return;
        }
        Node prev = findNodeOfIndex(index-1);
        prev.next = new Node(value,prev.next);
        size++;
    }

    // 删除指定节点
    @Override
    public T remove(int index) {
        checkIndex(index);
        if (index == 0) {
            head = head.next;
            size--;
            return head.value;
        }
        Node prev = findNodeOfIndex(index-1);
        T remove = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return remove;
    }

    // 设置指定节点值
    @Override
    public void set(int index, T value) {
        checkIndex(index);
        findNodeOfIndex(index).value = value;
    }

    // 获取节点值
    @Override
    public T get(int index) {
        checkIndex(index);
        return findNodeOfIndex(index).value;
    }

    // 判断容器是否为控
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 获取节点个数
    @Override
    public int size() {
        return size;
    }

    // 查找指定节点
    private Node findNodeOfIndex(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    // 判断下标是否合法
    private void checkIndex(int index) {
        if (index < 0 | index >= size ) {
            throw new IndexOutOfBoundsException("下标不合法");
        }

    }

    // 测试呈现
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
