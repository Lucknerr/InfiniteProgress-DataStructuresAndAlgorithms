package com.zl.class002;

/**
 * 选择排序
 */
public class SelectionSort {
    /**
     * 选择排序
     * @param nums 被排序的数组
     */
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

    /**
     * 数组中两指定位置元素交换
     * @param nums 被处理数组
     * @param i 指定位置下标
     * @param j 指定位置下标
     */
    public static void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
    }
}
