package com.zl.mergesort;

// 归并排序
public class MergeSort {

    /**
     * 入口函数
     * @param arr 用户输入数组
     */
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        f(arr,0,arr.length-1);
    }

    /**
     * 将数组划分左右子数组
     * @param arr 用户输入数组
     * @param l 左边界
     * @param r 右边界
     */
    private void f(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        // 找二份线
        int m = l + ((r - l) >> 1);
        // l - m 位置的子数组 归并
        f(arr,l,m);
        // m+1 - r 位置的子数组 归并
        f(arr,m+1,r);
        // 左右子数组排序
        merge(arr,l,r,m);
    }

    /**
     * 左右子数组排序元素
     * @param arr 用户输入数组
     * @param l 左边界
     * @param r 右边界
     * @param m 二分线
     */
    private void merge(int[] arr, int l, int r, int m) {
        // 需要辅助数组的长度N
        int N = r - l + 1;
        // 申请辅助数组
        int[] help = new int[N];
        // 辅助数组指针
        int i = 0;
        // 左子数组指针
        int left = l;
        // 右子数组指针
        int right = m+1;
        // 当左右指针都没越界时
        while (left <= m && right <= r) {
            // 谁小copy谁
            help[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        // 当右指针越界
        while (left <= m) {
            // 只需要copy左指针
            help[i++] = arr[left++];
        }
        // 当左指针越界
        while (right <= r) {
            // 只需要copy右指针
            help[i++] = arr[right++];
        }
        // 将辅助数组copy回原数组
        for (int j = 0; j < N; j++) {
            // 从原数组开始的位置
            arr[l + j] = help[j];
        }
    }
}
