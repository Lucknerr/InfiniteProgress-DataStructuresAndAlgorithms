package com.zl.list.impl;

import com.zl.list.List;

public class test {
    public static void main(String[] args) {
        List list = new DynamicArray();
        list.add(0,0);
//        System.out.println(list.toString());
//        list.add(1,2);
//        System.out.println(list.toString());
//        list.add(0,9);
//        System.out.println(list.toString());
//        list.add(2,3);
//        System.out.println(list.toString());
//        list.add(1,4);
//        System.out.println(list.toString());
//        System.out.println(list.get(4));
        list.remove(0);
        System.out.println(list.toString());
//        list.remove(1);
//        System.out.println(list.toString());
//        list.remove(1);
//        System.out.println(list.toString());
//        list.set(1,9);
//        System.out.println(list.toString());
    }
    
}
