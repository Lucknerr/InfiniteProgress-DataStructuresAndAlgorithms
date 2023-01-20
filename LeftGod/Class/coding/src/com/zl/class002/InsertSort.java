package com.zl.class002;

/**
 * 插入排序
 */
public class InsertSort {
    /**
     * 插入排序
     * @param nums 被排序的数组
     */
    public static void insertSort(int[] nums) {
        for (int i = 1;i < nums.length;i++) {
            for (int j = i;j > 0;j--) {
                if (nums[j] < nums[j-1]) {
                    swap(nums,j,j-1);
                }
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
