package com.zl;

import com.zl.list.List;
import com.zl.list.impl.DynamicArray;
import com.zl.list.impl.SingleLinkedList;

public class test {
    public static void main(String[] args) {
        List list = new DynamicArray();
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(1,0);
        System.out.println(list.toString());
        list.add(1,2);
        System.out.println(list.toString());
        list.add(2,3);
        System.out.println(list.toString());
        list.add(3,4);
        System.out.println(list.toString());
        list.add(2,4);
        System.out.println(list.toString());
        System.out.println(list.remove(0));
        System.out.println(list.toString());
        list.remove(4);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.set(0,1);
        System.out.println(list.toString());
        list.set(1,1);
        System.out.println(list.toString());
        list.set(2,5);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.remove(1);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
        list.add(0,0);
        System.out.println(list.toString());
    }
}
