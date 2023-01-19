package com.zl.class002;

public class BubbleSort {
    public static void bubbleSort(int[] nums) {
        for (int i = nums.length-1;i >= 0;i--) {
            for (int j = 0;j < i;j++) {
                if (nums[j] > nums[j+1]) {
                    swap(nums,j,j+1);
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
