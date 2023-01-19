package com.zl.samllsum;

// 小和问题
public class SmallSum {

    /**
     *
     * @param arr 用户输入数组
     */
    public int smallSum(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        return smallSum(arr,0,arr.length-1);
    }

    /**
     *
     * @param arr 用户输入数组
     * @param l 左边界
     * @param r 右边界
     */
    private int smallSum(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return smallSum(arr,l,m) + smallSum(arr,m+1,r) + merge(arr,l,r,m);
    }

    /**
     *
     * @param arr 用户输入数组
     * @param l 左边界
     * @param r 右边界
     * @param m 二分线
     */
    private int merge(int[] arr, int l, int r, int m) {
        int N = r - l + 1;
        int[] help = new int[N];
        int ans = 0;
        int i = 0;
        int left = l;
        int right = m+1;
        while (left <= m && right <= r) {
            if (arr[left] < arr[right]) {
                ans += (r - right + 1) * arr[left];
                help[i++] = arr[left++];
            } else {
                help[i++] = arr[right++];
            }
        }
        while (left <= m) {
            help[i++] = arr[left++];
        }
        while (right <= r) {
            help[i++] = arr[right++];
        }
        for (int j = 0; j < N; j++) {
            arr[l+j] = help[j];
        }
        return ans;
    }
}
