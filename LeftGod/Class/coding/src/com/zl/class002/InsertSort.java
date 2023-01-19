package com.zl.class002;

public class InsertSort {
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
