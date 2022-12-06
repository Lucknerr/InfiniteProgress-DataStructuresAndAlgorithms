package com.zl;

public class test {
    public static void main(String[] args) {
        BigRootHeap<Integer> bigRootHeap = new BigRootHeap<>(15);
        bigRootHeap.insert(9);
        bigRootHeap.insert(5);
        bigRootHeap.insert(8);
        bigRootHeap.insert(2);
        bigRootHeap.insert(3);
        bigRootHeap.insert(7);
        bigRootHeap.insert(6);
        bigRootHeap.insert(1);
        bigRootHeap.insert(10);
        bigRootHeap.insert(12);
        while (!bigRootHeap.isEmpty()) {
            bigRootHeap.pop();
            System.out.println(bigRootHeap.toString());
        }

    }
}
