package com.zl;

public class test {
    public static void main(String[] args) {
        DynamicArray<Integer> array = new DynamicArray<>(14);

        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        array.add(5);
        array.add(6);
        array.add(7);
        array.add(3, 7);
        array.add(3, 10);
        array.add(3, 8);
        array.add(3, 4);
        array.add(3, 4);
        array.add(12, 4);
        array.add(12, 4);
        array.add(12, 4);
        array.add(12, 4);
        array.add(12, 4);
        array.remove(6);
        array.remove(1);
        array.remove(2);
        array.add(3, 8);
        array.remove(2);
        array.add(3, 8);
        System.out.println(array.toString());
//        int[] arr = new int[]{1,2,3,4,5};
//        int i = 0;
//        arr[i++] = arr[i];
//        System.out.println(arr[i]);
    }
}
