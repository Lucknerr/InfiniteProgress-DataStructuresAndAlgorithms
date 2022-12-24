package com.zl.trappingrainwater;

// 42. 接雨水:https://leetcode.cn/problems/trapping-rain-water/description/
public class TrappingRainWater {

    /**
     * 动态规划 没朋暴力递归的过程
     * @param height 用户输入数组
     * @return 返回能接到的雨水单位
     */
    public int trap1(int[] height) {
        int N = height.length;
        // 创建一个从左向右遍历的高度差数组
        int[] tmp = new int[N];
        // 获取当前位置及之前位置的最大高度
        int max = height[0];
        // 从左向右遍历
        for (int i = 1;i < N;i++) {
            // 如果最大高度没有新高度高
            if (max <= height[i]) {
                // 更新最大高度
                max = height[i];
                // 与下一个位置的高度差为0
                tmp[i] = 0;
            } else {
                // 如果最大高度与下一个位置的存在高度差
                // 记录高度差
                tmp[i] = max - height[i];
            }
        }
        // 创建一个从右向左遍历的高度差数组
        int[] ans = new int[N];
        // 获取当前位置及之前位置的最大高度
        max = height[N-1];
        // 从右向左遍历
        for (int i = N-2;i >= 0;i--) {
            // 如果最大高度没有新高度高
            if (max <= height[i]) {
                // 更新最大高度
                max = height[i];
                // 与下一个位置的高度差为0
                ans[i] = 0;
            } else {
                // 如果最大高度与下一个位置的存在高度差
                // 记录高度差
                ans[i] = max - height[i];
            }
        }
        // 将max用来记录两个数组之间统一位置的最小值并且累加前面计算过的结果
        max = 0;
        for (int i = 0;i < N;i++) {
            max += Math.min(tmp[i],ans[i]);
        }
        return max;
    }

    /**
     * 双指针
     * @param height 用户输入数组
     * @return 返回能接到的雨水单位
     */
    public int trap2(int[] height) {
        // 左指针
        int l = 0;
        // 右指针
        int r = height.length-1;
        // 左指针遍历到的最大高度
        int lMax = height[l];
        // 右指针遍历到的最大高度
        int rMax = height[r];
        // 记录答案
        int ans = 0;
        while (l < r) {
            // 更新左指针遍历到的最大高度
            lMax = Math.max(lMax,height[l]);
            // 更新右指针遍历到的最大高度
            rMax = Math.max(rMax,height[r]);
            // 如果左最大高度比右最大高度低
            if (lMax < rMax) {
                // 放心计算左指针的高度差
                // 并且左指针向右遍历
                ans += lMax - height[l++];
            } else { // 否则
                // 放心计算右指针的高度差
                // 并且右指针向左遍历
                ans += rMax - height[r--];
            }
        }
        return ans;
    }
}
