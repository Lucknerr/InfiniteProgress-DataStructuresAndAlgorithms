package com.zl;

public class test {
    public static void main(String[] args) {
        BothwayLinkedList bothwayLinkedList = new BothwayLinkedList();
        bothwayLinkedList.add(0,0);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.add(1,2);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.add(0,9);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.add(2,3);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.add(1,4);
        System.out.println(bothwayLinkedList.toString());
        System.out.println(bothwayLinkedList.get(4));
        bothwayLinkedList.remove(1);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.remove(1);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.remove(1);
        System.out.println(bothwayLinkedList.toString());
        bothwayLinkedList.set(1,9);
        System.out.println(bothwayLinkedList.toString());




    }
}
