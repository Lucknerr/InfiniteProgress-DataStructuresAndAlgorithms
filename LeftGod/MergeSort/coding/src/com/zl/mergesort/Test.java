package com.zl.mergesort;

public class Test {
    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        int[] arr = {4,8,6,2,7,9,3,4,};
        mergeSort.mergeSort(arr);
        for (int a : arr) {
            System.out.println(a);
        }
    }
}
