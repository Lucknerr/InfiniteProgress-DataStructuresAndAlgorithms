package com.zl.zerosumpacksack;

// 01背包问题 暴力递归->动态规划
public class ZeroSumPacksack {

    /**
     * 暴力递归
     * 获取背包能装下物品的最大价值
     * @param w 每个物品的重量52
     * @param v 每个物品的价值
     * @param bag 背包总装载重量
     * @return 能装下物品的最大价值
     */
    public int maxVal(int[] w, int[] v, int bag) {
        return maxVal(w,v,0,bag);
    }

    /**
     * 获取剩下背包能装下物品的最大价值
     * @param w 每个物品的重量
     * @param v 每个物品的价值
     * @param index 当前物品
     * @param res 背包剩余装载量
     * @return 背包剩下空间能装下物品的最大价值
     */
    private int maxVal(int[] w, int[] v, int index, int res) {
        // 剩余容量小于0 说明不能在装了或者物品都挑完了没有剩下的物品了都返回价值0;
        if (res < 0 || index == w.length) {
            return 0;
        }
        // 预判背包剩余容量
        int tmp = res-w[index];
        // 尝试1: 如果预判容易大于等于0,
        // 那么可以尝试选择装入index物品记下index物品价值并且加上剩余物品背包能装下的最大价值,
        // 背包容量减少,index去到下一个物品
        // 如果预判容量小于0
        // 不选择index物品,返回价值0
        int p1 = tmp >= 0 ? v[index] + maxVal(w,v,index+1,tmp) : 0;
        // 尝试2: 不选择index物品,index去到下一个物品,背包容量不减少,记剩余物品背包能装下的最大价值
        int p2 = maxVal(w,v,index+1,res);
        // 返回两种尝试的最大值就是背包能装的最大价值
        return Math.max(p1,p2);
    }

    /**
     * 动态规划
     * 获取背包能装下物品的最大价值
     * @param w 每个物品的重量
     * @param v 每个物品的价值
     * @param bag 背包总装载重量
     * @return 能装下物品的最大价值
     */
    public int maxValue(int[] w, int[] v, int bag) {
        // 依赖动态规划表
        int[][] max = new int[w.length][bag+1];
        for (int index = w.length-2; index >= 0 ;index--) {
            for (int res = 0; res <= bag; res++) {
                int tmp = res - w[index];
                int p1 = tmp >= 0 ? v[index] + max[index+1][tmp] : 0;
                int p2 = max[index+1][res];
                max[index][res] = Math.max(p1,p2);
            }
        }
        return max[0][bag];
    }
}
