package com.zl.class002;

// 选择排序
public class SelectionSort {
    public static void selectSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                min = nums[j] < nums[min] ? j : min;
            }
            if (i != min) {
                swap(nums, i, min);
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
