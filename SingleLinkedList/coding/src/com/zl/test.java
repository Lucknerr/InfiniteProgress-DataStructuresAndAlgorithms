package com.zl;

public class test {
    public static void main(String[] args) {
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(0,0);
        singleLinkedList.add(0,1);
        singleLinkedList.add(0,2);
        singleLinkedList.add(0,3);
        singleLinkedList.add(1,3);
        singleLinkedList.add(0,3);
        singleLinkedList.add(2,0);
        System.out.println(singleLinkedList.get(0));
        singleLinkedList.set(3,1);
        System.out.println(singleLinkedList.remove(4));
        System.out.println(singleLinkedList.toString());
    }
}
