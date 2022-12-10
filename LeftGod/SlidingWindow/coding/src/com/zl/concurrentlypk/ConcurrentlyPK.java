package com.zl.concurrentlypk;

import java.util.Arrays;

public class ConcurrentlyPK {

    /**
     * 滑动窗口
     * @param arr 每个人的能力值
     * @param k 能力差值
     * @return 最多可以同时进行多少场比赛
     */
    public int PK(int[] arr, int k) {
        // 先排序
        Arrays.sort(arr);
        // 记录每个人是否配对过
        boolean[] tmp = new boolean[arr.length];
        // 快指针
        int l = 0;
        // 慢指针
        int r = 0;
        // 记录配对数
        int ans = 0;
        while (r < arr.length) {
            // 如果被配对过
            if (tmp[l]) {
                // l去到下一个位置
                l++;
            }else if (l == r) { // 如果是同一个人
                // r去到下一个位置
                r++;
            } else if (arr[r] - arr[l] == k) { // 如果匹配成功
                // 将r位置标记为配对过
                tmp[r] = true;
                // 记录一次配对
                ans++;
                // l r 都去到下一个位置
                l++;
                r++;
            } else if (arr[r] - arr[l] > k) { // 如果l r 大于匹配范围
                // l r 都去到下一个位置
                l++;
                r++;
            } else { // 如果上文情况都不成立
                // r 去到下一个位置
                r++;
            }
        }
        return ans;
    }

    /**
     * 暴力递归
     * @param arr 每个人的能力值
     * @param k 能力差值
     * @return 最多可以同时进行多少场比赛
     */
    public int PK1(int arr, int k) {

    }

    /**
     *
     * @param arr 每个人的能力值
     * @param k 能力差值
     * @param i 第i号人
     * @return 最多可以同时进行多少场比赛
     */
    public int f(int[] arr, boolean[] sta, int k, int i) {
        if(i == arr.length) {
            return 0;
        }
        sta[i] = true;
        for (int j = 0;j < arr.length;j++) {
            boolean[] s = new boolean[arr.length];
            if (!sta[i]) {
                arr[i] + arr[j] == k ? 1 + f()
            }
        }
    }
}
