package com.zl.samllsum;


import java.sql.SQLOutput;

public class Test {
    public static void main(String[] args) {
        SmallSum smallSum = new SmallSum();
        int[] arr = {4,8,6,2,7,9,3,4,};
        System.out.println(smallSum.smallSum(arr));
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
