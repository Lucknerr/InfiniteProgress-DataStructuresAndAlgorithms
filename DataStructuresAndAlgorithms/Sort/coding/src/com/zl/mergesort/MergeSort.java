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
        int m = l + ((r - l) >> 1);
        f(arr,l,m);
        f(arr,m+1,r);
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
        int N = r - l + 1;
        int[] help = new int[N];
        int i = 0;
        int left = l;
        int right = m+1;
        while (left <= m && right <= r) {
            help[i++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while (left <= m) {
            help[i++] = arr[left++];
        }
        while (right <= r) {
            help[i++] = arr[right++];
        }
        for (int j = 0; j < N; j++) {
            arr[l + j] = help[j];
        }
    }
}
